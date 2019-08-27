package com.ramitsuri.depressiontracker.fragment;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
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
import com.ramitsuri.depressiontracker.google.AccountManager;
import com.ramitsuri.depressiontracker.google.SignInResponse;
import com.ramitsuri.depressiontracker.utils.PrefHelper;
import com.ramitsuri.depressiontracker.viewModel.HistoryViewModel;
import com.ramitsuri.depressiontracker.work.BackupWorker;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
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
            initiateBackup(response.getGoogleSignInAccount().getAccount());
        } else if (response.getGoogleSignInIntent() != null) {
            // request account access
            startActivityForResult(response.getGoogleSignInIntent(),
                    Constants.RequestCode.GOOGLE_SIGN_IN);
        }
    }

    private void initiateBackup(Account account) {
        String spreadsheetId =
                PrefHelper.get(getString(R.string.settings_key_spreadsheet_id), null);
        String sheetId = PrefHelper.get(getString(R.string.settings_key_sheet_id), null);
        Data.Builder builder = new Data.Builder();
        builder.putString(Constants.Work.APP_NAME, getString(R.string.app_name));
        builder.putString(Constants.Work.ACCOUNT_NAME, account.name);
        builder.putString(Constants.Work.ACCOUNT_TYPE, account.type);
        builder.putString(Constants.Work.SPREADSHEET_ID, spreadsheetId);
        builder.putString(Constants.Work.SHEET_ID, sheetId);

        OneTimeWorkRequest backupRequest = new OneTimeWorkRequest.Builder(BackupWorker.class)
                .addTag(Constants.TAG_ONE_TIME_BACKUP)
                .setInputData(builder.build())
                .build();
        WorkManager.getInstance(MainApplication.getInstance()).enqueue(backupRequest);
        WorkManager.getInstance(MainApplication.getInstance())
                .getWorkInfoByIdLiveData(backupRequest.getId()).observe(getViewLifecycleOwner(),
                new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null) {
                            if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                Timber.i("Backup Success");
                            } else if (workInfo.getState() == WorkInfo.State.RUNNING) {
                                Timber.i("Backup Running");
                            } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                                Timber.i("Backup Fail");
                            }
                        }
                    }
                });
        scheduleBackup(account);
    }

    private void scheduleBackup(Account account) {
        String spreadsheetId =
                PrefHelper.get(getString(R.string.settings_key_spreadsheet_id), null);
        String sheetId = PrefHelper.get(getString(R.string.settings_key_sheet_id), null);
        Data.Builder builder = new Data.Builder();
        builder.putString(Constants.Work.APP_NAME, getString(R.string.app_name));
        builder.putString(Constants.Work.ACCOUNT_NAME, account.name);
        builder.putString(Constants.Work.ACCOUNT_TYPE, account.type);
        builder.putString(Constants.Work.SPREADSHEET_ID, spreadsheetId);
        builder.putString(Constants.Work.SHEET_ID, sheetId);

        String workTag = Constants.TAG_SCHEDULED_BACKUP;
        Constraints myConstraints = new Constraints.Builder()
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();

        PeriodicWorkRequest.Builder periodicWorkRequestBuilder =
                new PeriodicWorkRequest.Builder(BackupWorker.class, 12, TimeUnit.HOURS)
                        .setConstraints(myConstraints)
                        .addTag(workTag);
        PeriodicWorkRequest request = periodicWorkRequestBuilder.build();
        WorkManager.getInstance(MainApplication.getInstance()).enqueueUniquePeriodicWork(workTag,
                ExistingPeriodicWorkPolicy.KEEP, request);

        WorkManager.getInstance(MainApplication.getInstance()).getWorkInfosByTagLiveData(workTag)
                .observe(this,
                        new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfos) {
                                if (workInfos != null && !workInfos.isEmpty()) {
                                    Timber.i("Work status %s", workInfos.get(0).toString());
                                }
                            }
                        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> getAccountTask =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            if (getAccountTask.isSuccessful() && getAccountTask.getResult() != null) {
                initiateBackup(getAccountTask.getResult().getAccount());
            } else {
                Timber.i("Sign-in failed.");
            }
        }
    }
}
