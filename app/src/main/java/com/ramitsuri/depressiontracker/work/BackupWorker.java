package com.ramitsuri.depressiontracker.work;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;

import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.constants.Constants;
import com.ramitsuri.depressiontracker.data.DepressionTrackerDatabase;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.sheetscore.consumerResponse.InsertConsumerResponse;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import timber.log.Timber;

public class BackupWorker extends Worker {

    public BackupWorker(@NonNull Context context,
            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String appName = getInputData().getString(Constants.Work.APP_NAME);
        String spreadsheetId = getInputData().getString(Constants.Work.SPREADSHEET_ID);
        String accountName = getInputData().getString(Constants.Work.ACCOUNT_NAME);
        String accountType = getInputData().getString(Constants.Work.ACCOUNT_TYPE);
        String sheetId = getInputData().getString(Constants.Work.SHEET_ID);

        if (appName == null || TextUtils.isEmpty(spreadsheetId) || TextUtils.isEmpty(sheetId) ||
                TextUtils.isEmpty(accountName) || TextUtils.isEmpty(accountType)) {
            Timber.w("App Name - %s / Spreadsheet Id - %s / Sheet Id - %s / Account Name - %s / " +
                            "Account Type - %s null or empty", appName, spreadsheetId, sheetId,
                    accountName, accountType);
            return Result.retry();
        }

        Account account = new Account(accountName, accountType);

        if (MainApplication.getInstance().getSheetRepository() == null) {
            Timber.w("Sheet repo null");
            MainApplication.getInstance()
                    .initSheetRepo(account, spreadsheetId, Arrays.asList(Constants.SCOPES));
        }

        List<Question> questionsToBackup =
                DepressionTrackerDatabase.getInstance().questionDao().getAllUnsynced();

        if (questionsToBackup == null) {
            Timber.w("Questions to backup is null");
            return Result.retry();
        }

        InsertConsumerResponse response = MainApplication.getInstance().getSheetRepository()
                .getInsertRangeResponse(questionsToBackup, sheetId);
        if (response.isSuccessful()) {
            MainApplication.getInstance().getQuestionRepository().deleteAll();
            return Result.success();
        }
        return Result.failure();
    }
}
