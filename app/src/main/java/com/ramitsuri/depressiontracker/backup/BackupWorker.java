package com.ramitsuri.depressiontracker.backup;

import android.content.Context;
import android.text.TextUtils;

import com.google.android.gms.common.api.Response;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.constants.Constants;
import com.ramitsuri.depressiontracker.entities.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
/*
public class BackupWorker extends Worker {
    public BackupWorker(@NonNull Context context,
            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        LoaderResponse response = backup("", getApplicationContext().getString(R.string.app_name), AppHelper.getSpreadsheetId());
        if (response.getResponseCode() == LoaderResponse.FAILURE) {
            return Result.failure();
        } else {
            return Result.success();
        }
    }

    private Response backup(String accountName, String appName, String spreadsheetId) {
        GoogleAccountCredential credential =
                GoogleAccountCredential.usingOAuth2(getApplicationContext(), Arrays.asList(
                        Constants.SCOPES)).setBackOff(new ExponentialBackOff());
        credential.setSelectedAccountName(accountName);
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        com.google.api.services.sheets.v4.Sheets service =
                new com.google.api.services.sheets.v4.Sheets.Builder(
                        transport, jsonFactory, credential)
                        .setApplicationName(appName)
                        .build();

        BatchUpdateSpreadsheetRequest content = new BatchUpdateSpreadsheetRequest();
        List<Request> requests = new ArrayList<>();
        List<Question> questionsToBackup = ExpenseHelper.getExpensesRequiringBackup();
        Request expensesRequest = SheetsHelper.getExpenseSheetsRequest(questionsToBackup);
        requests.add(expensesRequest);
        content.setRequests(requests);
        Sheets.Spreadsheets.BatchUpdate batchUpdate;
        try {
            if (!TextUtils.isEmpty(spreadsheetId)) {
                batchUpdate = service.spreadsheets().batchUpdate(spreadsheetId, content);
                batchUpdate.execute();

                ExpenseHelper.updateSyncStatusAfterBackup(questionsToBackup);
                ExpenseHelper.deleteBackedUpExpenses();
                AppHelper.setFirstBackupComplete(true);
                AppHelper.setLastBackupTime(System.currentTimeMillis());
                return new LoaderResponse(LoaderResponse.SUCCESS, null, null);
            } else {
                return new LoaderResponse(LoaderResponse.FAILURE, null, null);
            }
        } catch (UserRecoverableAuthIOException e) {
            return new LoaderResponse(LoaderResponse.FAILURE, e.getIntent(), null);
        } catch (IOException e) {
            return new LoaderResponse(LoaderResponse.FAILURE, null, null);
        }
    }
}*/
