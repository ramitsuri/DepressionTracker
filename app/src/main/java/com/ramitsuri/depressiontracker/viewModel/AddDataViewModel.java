package com.ramitsuri.depressiontracker.viewModel;

import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.data.repository.QuestionRepository;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.intdef.AnswerType;
import com.ramitsuri.depressiontracker.intdef.DifficultyType;
import com.ramitsuri.depressiontracker.utils.QuestionHelper;

import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import timber.log.Timber;

public class AddDataViewModel extends ViewModel {
    private QuestionRepository mQuestionRepository;

    private List<Question> mQuestions;

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
                return mQuestions.get(mCurrentIndex.getValue());
            }
        }
        return null;
    }

    public LiveData<Integer> getCurrentIndex() {
        return mCurrentIndex;
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void updateQuestion(@AnswerType int answer, @DifficultyType int difficulty) {
        if (mCurrentIndex.getValue() != null) {
            Question question = mQuestions.get(mCurrentIndex.getValue());
            if (question != null) {
                question.setAnswerType(answer);
                question.setDifficultyType(difficulty);
                mQuestions.set(mCurrentIndex.getValue(), question);
            } else {
                Timber.e("Question requested to be updated is null");
            }
        }
    }

    public LiveData<Boolean> insertQuestions() {
        for (int i = 0; i < mQuestions.size(); i++) {
            Question question = mQuestions.get(i);
            question.setDate(new Date().getTime());
            mQuestions.set(i, question);
        }
        return mQuestionRepository.insertQuestions(mQuestions);
    }
}
