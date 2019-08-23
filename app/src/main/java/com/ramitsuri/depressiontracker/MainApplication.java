package com.ramitsuri.depressiontracker;

import android.accounts.Account;
import android.app.Application;

import com.ramitsuri.depressiontracker.data.DepressionTrackerDatabase;
import com.ramitsuri.depressiontracker.data.DummyData;
import com.ramitsuri.depressiontracker.data.repository.QuestionRepository;
import com.ramitsuri.depressiontracker.data.repository.SheetRepository;
import com.ramitsuri.depressiontracker.logging.ReleaseTree;

import java.util.List;

import androidx.annotation.NonNull;
import timber.log.Timber;

public class MainApplication extends Application {

    private QuestionRepository mQuestionRepository;

    private SheetRepository mSheetRepository;

    private static MainApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        initTimber();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    public static MainApplication getInstance() {
        return sInstance;
    }

    public void initDataRepos() {
        AppExecutors appExecutors = AppExecutors.getInstance();
        DepressionTrackerDatabase database = DepressionTrackerDatabase.getInstance();

        mQuestionRepository = new QuestionRepository(appExecutors, database);

        // TODO debug
        mQuestionRepository.deleteAll();
        mQuestionRepository.insertQuestions(DummyData.getQuestions());
    }

    public void initSheetRepo(@NonNull Account account,
            @NonNull String spreadsheetId,
            @NonNull List<String> scopes) {
        AppExecutors appExecutors = AppExecutors.getInstance();
        String appName = getString(R.string.app_name);

        mSheetRepository =
                new SheetRepository(this, appName, account, spreadsheetId, scopes, appExecutors);
    }

    public QuestionRepository getQuestionRepository() {
        return mQuestionRepository;
    }

    public SheetRepository getSheetRepository() {
        return mSheetRepository;
    }
}
