package com.ramitsuri.depressiontracker.viewModel;

import com.ramitsuri.depressiontracker.MainApplication;
import com.ramitsuri.depressiontracker.data.repository.QuestionRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class HistoryViewModel extends ViewModel {
    private QuestionRepository mQuestionRepository;
    private LiveData<List<Long>> mDates;

    public HistoryViewModel() {
        super();

        MainApplication.getInstance().initDataRepos();
        mQuestionRepository = MainApplication.getInstance().getQuestionRepository();

        mDates = mQuestionRepository.getQuestionDates();
    }

    public LiveData<List<Long>> getDates() {
        return mDates;
    }
}
