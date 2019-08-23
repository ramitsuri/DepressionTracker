package com.ramitsuri.depressiontracker.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.data.repository.SheetRepository;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainApplication.getInstance().initRepos();

        signIn();
    }

    /**
     * Sign in
     * Enable Sheets API console.cloud.google.com/apis/library/sheets.googleapis.com
     * Create Android Client key
     */

    protected void signIn() {
        Set<Scope> requiredScopes = new HashSet<>(2);
        requiredScopes.add(new Scope(SheetsScopes.SPREADSHEETS));
        requiredScopes.add(new Scope(SheetsScopes.DRIVE));
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null && signInAccount.getGrantedScopes().containsAll(requiredScopes)) {
            SheetRepository repository = MainApplication.getInstance().getSheetRepository();
            repository.getSheets(signInAccount);
        } else {
            GoogleSignInOptions signInOptions =
                    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestScopes(new Scope(SheetsScopes.SPREADSHEETS))
                            .requestScopes(new Scope(SheetsScopes.DRIVE))
                            .requestEmail()
                            .build();
            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, signInOptions);
            startActivityForResult(googleSignInClient.getSignInIntent(), 1001);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            String a = "";
            Task<GoogleSignInAccount> getAccountTask =
                    GoogleSignIn.getSignedInAccountFromIntent(data);
           // if (getAccountTask.isSuccessful()) {
                SheetRepository repository = MainApplication.getInstance().getSheetRepository();
                repository.getSheets(getAccountTask.getResult());
            /*} else {
                Timber.i("Sign-in failed.");
            }*/
        }
    }
}
