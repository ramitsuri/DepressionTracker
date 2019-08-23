package com.ramitsuri.depressiontracker.data.repository;

import android.content.Context;
import android.text.TextUtils;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.ramitsuri.depressiontracker.AppExecutors;
import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.constants.Constants;
import com.ramitsuri.depressiontracker.entities.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import timber.log.Timber;

public class SheetRepository {

    private AppExecutors mExecutors;

    public SheetRepository(AppExecutors executors) {
        mExecutors = executors;
    }

    public LiveData<String> getSheets(final GoogleSignInAccount account) {
        final MutableLiveData<List<String>> questions = new MutableLiveData<>();
        mExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                getData(account);
                //questions.postValue(values);
            }
        });
        //return questions;
        return null;
    }

    @WorkerThread
    private void getData(GoogleSignInAccount account) {
        Context context = MainApplication.getInstance().getApplicationContext();
        GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                context, Arrays.asList(Constants.SCOPES)).setBackOff(new ExponentialBackOff());
        //credential.setSelectedAccountName(AppHelper.getAccountName());
        //credential.setSelectedAccountName("ramitsuri007@gmail.com");
        credential.setSelectedAccount(account.getAccount());
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        com.google.api.services.sheets.v4.Sheets service =
                new com.google.api.services.sheets.v4.Sheets.Builder(
                        transport, jsonFactory, credential)
                        .setApplicationName(context.getString(R.string.app_name))
                        .build();

        try {
            String spreadsheetId = "1lq6b81N67OVBtZ_IlPV5A-aBKx3q8msBg0oyle9FmfE";
            //String spreadsheetId = AppHelper.getSpreadsheetId();
            if (!TextUtils.isEmpty(spreadsheetId)) {
                Sheets.Spreadsheets.Get request = service.spreadsheets().get(spreadsheetId);
                request.setIncludeGridData(false);

                Spreadsheet response = request.execute();
                Timber.i(response.toPrettyString());
                /*List<SheetDto> sheets = new ArrayList<>();
                for (Sheet sheet : response.getSheets()) {
                    int sheetId = sheet.getProperties().getSheetId();
                    String sheetName = sheet.getProperties().getTitle();
                    SheetDto sheetDto = new SheetDto(sheetId, sheetName);
                    sheets.add(sheetDto);
                }

                return sheets;*/
            } else {
                //return null;
            }
        } catch (UserRecoverableAuthIOException e) {
            Timber.e(e);
        } catch (IOException e) {
            Timber.e(e);
        }
    }
}
