package com.ramitsuri.depressiontracker.google;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class AccountManager {

    public SignInResponse prepareSignIn(@NonNull Context context, @NonNull String[] scopes) {
        SignInResponse response = new SignInResponse();
        List<Scope> requiredScopes = new ArrayList<>();
        for (String scopeString : scopes) {
            requiredScopes.add(new Scope(scopeString));
        }
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(context);
        if (signInAccount != null && signInAccount.getGrantedScopes().containsAll(requiredScopes)) {
            response.setGoogleSignInAccount(signInAccount);
        } else {
            GoogleSignInOptions.Builder builder =
                    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
            for (Scope scope : requiredScopes) {
                builder.requestScopes(scope);
            }
            builder.requestEmail();
            GoogleSignInOptions signInOptions = builder.build();

            GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(context, signInOptions);
            response.setGoogleSignInIntent(googleSignInClient.getSignInIntent());
        }
        return response;
    }
}
