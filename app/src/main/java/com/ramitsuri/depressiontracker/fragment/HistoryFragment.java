package com.ramitsuri.depressiontracker.fragment;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.adapter.HistoryAdapter;
import com.ramitsuri.depressiontracker.constants.Constants;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.google.AccountManager;
import com.ramitsuri.depressiontracker.google.SignInResponse;
import com.ramitsuri.depressiontracker.spreadsheet.consumerResponse.InsertConsumerResponse;
import com.ramitsuri.depressiontracker.utils.PrefHelper;
import com.ramitsuri.depressiontracker.viewModel.HistoryViewModel;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class HistoryFragment extends BaseFragment {
    private HistoryViewModel mViewModel;
    private HistoryAdapter mAdapter;

    public HistoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnBackup = view.findViewById(R.id.btn_backup);
        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        FloatingActionButton btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HistoryFragment.this)
                        .navigate(R.id.nav_action_add_data, null);
            }
        });

        setupList(view);
    }

    private void setupList(View view) {
        if (mAdapter == null) {
            mAdapter = new HistoryAdapter();
        }

        RecyclerView listExpenses = view.findViewById(R.id.list_dates);
        listExpenses.setLayoutManager(new LinearLayoutManager(getActivity()));
        listExpenses.setAdapter(mAdapter);

        // NOTE: Observer is triggered multiple times because of
        // 1. lifecycle state changing from inactive to active and
        // 2. data update
        mViewModel.getDates().observe(getViewLifecycleOwner(), mObserver);
    }

    private Observer<List<Long>> mObserver = new Observer<List<Long>>() {
        @Override
        public void onChanged(List<Long> dates) {
            Timber.i("Refreshing expenses");
            if (mAdapter != null) {
                mAdapter.setHistory(dates);
            }
        }
    };

    private void signIn() {
        AccountManager accountManager = new AccountManager();
        SignInResponse response =
                accountManager.prepareSignIn(MainApplication.getInstance(), Constants.SCOPES);
        if (response.getGoogleSignInAccount() != null) {
            String spreadsheetId =
                    PrefHelper.get(getString(R.string.settings_key_spreadsheet_id), null);
            String sheetId = PrefHelper.get(getString(R.string.settings_key_sheet_id), null);
            initiateBackup(response.getGoogleSignInAccount().getAccount(), spreadsheetId, sheetId);
        } else if (response.getGoogleSignInIntent() != null) {
            // request account access
            startActivityForResult(response.getGoogleSignInIntent(),
                    Constants.RequestCode.GOOGLE_SIGN_IN);
        }
    }

    public void initiateBackup(Account account, final String spreadsheetId, final String sheetId) {
        if (!TextUtils.isEmpty(spreadsheetId)) {
            MainApplication.getInstance()
                    .initSheetRepo(account, spreadsheetId, Arrays.asList(Constants.SCOPES));
            if (!TextUtils.isEmpty(sheetId)) {
                getUnsyncedQuestions(sheetId);
            }
        }
    }

    private void getUnsyncedQuestions(final String sheetId) {
        mViewModel.getQuestionRepository().getAllUnsynced().observe(
                getViewLifecycleOwner(), new Observer<List<Question>>() {
                    @Override
                    public void onChanged(List<Question> questions) {
                        backup(questions, sheetId);
                    }
                });
    }

    private void backup(List<Question> questions, final String sheetId) {
        LiveData<InsertConsumerResponse> responseLiveData =
                MainApplication.getInstance().getSheetRepository().insertRange(questions, sheetId);
        responseLiveData.observe(getViewLifecycleOwner(), new Observer<InsertConsumerResponse>() {
            @Override
            public void onChanged(InsertConsumerResponse insertConsumerResponse) {
                Timber.i(String.valueOf(insertConsumerResponse.isSuccessful()));
                if (insertConsumerResponse.isSuccessful()) {
                    deleteBackedUp();
                }
            }
        });
    }

    private void deleteBackedUp() {
        mViewModel.getQuestionRepository().deleteAll();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> getAccountTask =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            if (getAccountTask.isSuccessful() && getAccountTask.getResult() != null) {
                String spreadsheetId =
                        PrefHelper.get(getString(R.string.settings_key_spreadsheet_id), null);
                String sheetId = PrefHelper.get(getString(R.string.settings_key_sheet_id), null);
                initiateBackup(getAccountTask.getResult().getAccount(), spreadsheetId, sheetId);
            } else {
                Timber.i("Sign-in failed.");
            }
        }
    }
}
