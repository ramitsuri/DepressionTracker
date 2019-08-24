package com.ramitsuri.depressiontracker.viewModel;

import android.util.SparseArray;

import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.data.repository.QuestionRepository;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.utils.QuestionHelper;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddDataViewModel extends ViewModel {
    private QuestionRepository mQuestionRepository;

    private SparseArray<Question> mQuestions;

    private MutableLiveData<Integer> mCurrentIndex;

    public AddDataViewModel() {
        super();

        MainApplication.getInstance().initDataRepos();
        mQuestionRepository = MainApplication.getInstance().getQuestionRepository();

        mQuestions = QuestionHelper.getBaseQuestions(MainApplication.getInstance());

        mCurrentIndex = new MutableLiveData<>();
        mCurrentIndex.postValue(0);
    }

    public void selectNextQuestion() {
        if (mCurrentIndex.getValue() != null) {
            mCurrentIndex.postValue(mCurrentIndex.getValue() + 1);
            if (mCurrentIndex.getValue() >= mQuestions.size()) {
                mCurrentIndex.postValue(0);
            }
        }
    }

    public void selectPreviousQuestion() {
        if (mCurrentIndex.getValue() != null) {
            mCurrentIndex.postValue(mCurrentIndex.getValue() - 1);
            if (mCurrentIndex.getValue() < 0) {
                mCurrentIndex.postValue(mQuestions.size() - 1);
            }
        }
    }

    @Nullable
    public Question getSelectedQuestion() {
        if (mCurrentIndex.getValue() != null) {
            if (mCurrentIndex.getValue() >= 0 && mCurrentIndex.getValue() < mQuestions.size()) {
                return mQuestions.get(mQuestions.keyAt(mCurrentIndex.getValue()));
            }
        }
        return null;
    }

    public LiveData<Integer> getCurrentIndex() {
        return mCurrentIndex;
    }

    public SparseArray<Question> getQuestions() {
        return mQuestions;
    }
}
