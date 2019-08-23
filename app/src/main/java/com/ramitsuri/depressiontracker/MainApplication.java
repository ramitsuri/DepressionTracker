package com.ramitsuri.depressiontracker;

import android.app.Application;

import com.ramitsuri.depressiontracker.data.DepressionTrackerDatabase;
import com.ramitsuri.depressiontracker.data.DummyData;
import com.ramitsuri.depressiontracker.data.repository.QuestionRepository;
import com.ramitsuri.depressiontracker.data.repository.SheetRepository;
import com.ramitsuri.depressiontracker.logging.ReleaseTree;

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

    public void initRepos() {
        AppExecutors appExecutors = AppExecutors.getInstance();
        DepressionTrackerDatabase database = DepressionTrackerDatabase.getInstance();

        mQuestionRepository = new QuestionRepository(appExecutors, database);
        mSheetRepository = new SheetRepository(appExecutors);

        // TODO debug
        mQuestionRepository.deleteAll();
        mQuestionRepository.insertQuestions(DummyData.getQuestions());
    }

    public QuestionRepository getQuestionRepository() {
        return mQuestionRepository;
    }

    public SheetRepository getSheetRepository() {
        return mSheetRepository;
    }
}
