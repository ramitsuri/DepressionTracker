package com.ramitsuri.depressiontracker.activity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.constants.Constants;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.google.AccountManager;
import com.ramitsuri.depressiontracker.google.SignInResponse;
import com.ramitsuri.depressiontracker.spreadsheet.consumerResponse.InsertConsumerResponse;
import com.ramitsuri.depressiontracker.utils.PrefHelper;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();

        signIn();
    }

    private void setupNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(toolbar, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.onNavDestinationSelected(item, navController);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> getAccountTask =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
            if (getAccountTask.isSuccessful() && getAccountTask.getResult() != null) {
                backUp(getAccountTask.getResult().getAccount());
            } else {
                Timber.i("Sign-in failed.");
            }
        }
    }

    private void signIn() {
        AccountManager accountManager = new AccountManager();
        SignInResponse response = accountManager.prepareSignIn(this, Constants.SCOPES);
        if (response.getGoogleSignInAccount() != null) {
            backUp(response.getGoogleSignInAccount().getAccount());
        } else if (response.getGoogleSignInIntent() != null) {
            // request account access
            startActivityForResult(response.getGoogleSignInIntent(),
                    Constants.RequestCode.GOOGLE_SIGN_IN);
        }
    }

    private void backUp(Account account) {
        MainApplication.getInstance().initDataRepos();
        String spreadsheetId =
                PrefHelper.get(getString(R.string.settings_key_spreadsheet_id), null);
        if (!TextUtils.isEmpty(spreadsheetId)) {
            MainApplication.getInstance()
                    .initSheetRepo(account, spreadsheetId, Arrays.asList(Constants.SCOPES));
            final String sheetId = PrefHelper.get(getString(R.string.settings_key_sheet_id), null);
            if (!TextUtils.isEmpty(sheetId)) {
                MainApplication.getInstance().getQuestionRepository().getAllUnsynced().observe(
                        this, new Observer<List<Question>>() {
                            @Override
                            public void onChanged(List<Question> questions) {
                                LiveData<InsertConsumerResponse> responseLiveData =
                                        MainApplication.getInstance().getSheetRepository()
                                                .insertRange(questions, sheetId);
                                responseLiveData.observe(MainActivity.this,
                                        new Observer<InsertConsumerResponse>() {
                                            @Override
                                            public void onChanged(
                                                    InsertConsumerResponse insertConsumerResponse) {
                                                Timber.i(String.valueOf(
                                                        insertConsumerResponse.isSuccessful()));
                                            }
                                        });
                            }
                        });
            }
        }
    }
}
