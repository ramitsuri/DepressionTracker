package com.ramitsuri.depressiontracker.data.repository;

import com.ramitsuri.depressiontracker.AppExecutors;
import com.ramitsuri.depressiontracker.data.DepressionTrackerDatabase;
import com.ramitsuri.depressiontracker.entities.Question;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class QuestionRepository {

    private AppExecutors mExecutors;
    private DepressionTrackerDatabase mDatabase;

    private LiveData<List<Long>> mQuestionDates;

    public QuestionRepository(AppExecutors appExecutors,
            DepressionTrackerDatabase database) {
        mExecutors = appExecutors;
        mDatabase = database;

        mQuestionDates = mDatabase.questionDao().getDates();
    }

    public LiveData<List<Long>> getQuestionDates() {
        return mQuestionDates;
    }

    public LiveData<List<Question>> getAll() {
        final MutableLiveData<List<Question>> questions = new MutableLiveData<>();
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Question> values = mDatabase.questionDao().getAll();
                questions.postValue(values);
            }
        });
        return questions;
    }

    public LiveData<List<Question>> getAllUnsynced() {
        final MutableLiveData<List<Question>> questions = new MutableLiveData<>();
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Question> values = mDatabase.questionDao().getAllUnsynced();
                questions.postValue(values);
            }
        });
        return questions;
    }

    public LiveData<List<Question>> getForDate(final long date) {
        final MutableLiveData<List<Question>> questions = new MutableLiveData<>();
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Question> values = mDatabase.questionDao().getForDate(date);
                questions.postValue(values);
            }
        });
        return questions;
    }

    public void insertQuestion(final Question question) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.questionDao().insert(question);
            }
        });
    }

    public void insertQuestions(final List<Question> questions) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.questionDao().insertAll(questions);
            }
        });
    }

    public void updateUnsynced() {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.questionDao().updateUnsynced();
            }
        });
    }

    public void deleteAll() {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.questionDao().deleteAll();
            }
        });
    }

    public void deleteSynced() {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.questionDao().deleteSynced();
            }
        });
    }
}
