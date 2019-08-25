package com.ramitsuri.depressiontracker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.adapter.AnswerListPickerAdapter;
import com.ramitsuri.depressiontracker.adapter.DifficultyListPickerAdapter;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.intdef.AnswerType;
import com.ramitsuri.depressiontracker.intdef.DifficultyType;
import com.ramitsuri.depressiontracker.viewModel.AddDataViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class AddDataFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mBtnPrev, mBtnNext;
    private FloatingActionButton mBtnDone;
    private TextView mTextQuestion;
    private RecyclerView mListAnswers, mListDifficulties;

    private AddDataViewModel mViewModel;

    public AddDataFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(AddDataViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(AddDataViewModel.class);

        setupViews(view);
        mViewModel.getCurrentIndex().observe(getViewLifecycleOwner(), mIndexObserver);
    }

    private void setupViews(View view) {
        mBtnPrev = view.findViewById(R.id.btn_prev);
        mBtnPrev.setOnClickListener(this);

        mBtnNext = view.findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);

        mTextQuestion = view.findViewById(R.id.text_question);

        List<Integer> answerValues = new ArrayList<>();
        answerValues.add(AnswerType.YES);
        answerValues.add(AnswerType.NO);
        mListAnswers = view.findViewById(R.id.list_answers);
        mListAnswers.setLayoutManager(
                new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        mListAnswers.setHasFixedSize(true);
        AnswerListPickerAdapter answerAdapter = new AnswerListPickerAdapter();
        answerAdapter.setValues(answerValues);
        answerAdapter.setCallback(new AnswerListPickerAdapter.ListPickerAdapterCallback() {
            @Override
            public void onItemPicked(Integer value) {
                onAnswerSelected(value);
            }
        });
        mListAnswers.setAdapter(answerAdapter);

        List<Integer> difficultyValues = new ArrayList<>();
        difficultyValues.add(DifficultyType.NOT_AT_ALL);
        difficultyValues.add(DifficultyType.SOMEWHAT);
        difficultyValues.add(DifficultyType.VERY);
        difficultyValues.add(DifficultyType.EXTREMELY);
        mListDifficulties = view.findViewById(R.id.list_difficulties);
        mListDifficulties.setLayoutManager(
                new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        mListDifficulties.setHasFixedSize(true);
        DifficultyListPickerAdapter difficultyAdapter = new DifficultyListPickerAdapter();
        difficultyAdapter.setValues(difficultyValues);
        difficultyAdapter.setCallback(new DifficultyListPickerAdapter.ListPickerAdapterCallback() {
            @Override
            public void onItemPicked(Integer value) {
                onDifficultySelected(value);
            }
        });
        mListDifficulties.setAdapter(difficultyAdapter);

        mBtnDone = view.findViewById(R.id.btn_done);
        mBtnDone.setOnClickListener(this);
    }

    private void onDifficultySelected(@DifficultyType Integer value) {
        // do nothing
    }

    private void onAnswerSelected(@AnswerType Integer value) {
        if (mListDifficulties == null) {
            return;
        }
        if (value == AnswerType.NO) {
            mListDifficulties.setVisibility(View.GONE);
            if (mListDifficulties.getAdapter() != null) {
                ((DifficultyListPickerAdapter)mListDifficulties.getAdapter())
                        .setSelectedValue(DifficultyType.NA);
            }
        } else {
            mListDifficulties.setVisibility(View.VISIBLE);
        }
    }

    private void updateDataViews() {
        Question question = mViewModel.getSelectedQuestion();
        if (question == null) {
            return;
        }
        if (mTextQuestion != null) {
            mTextQuestion.setText(question.getText());
        }
        if (mListDifficulties != null) {
            if (mListDifficulties.getAdapter() != null) {
                ((DifficultyListPickerAdapter)mListDifficulties.getAdapter())
                        .setSelectedValue(question.getDifficultyType());
                onDifficultySelected(question.getDifficultyType());
            }
        }
        if (mListAnswers != null) {
            if (mListAnswers.getAdapter() != null) {
                ((AnswerListPickerAdapter)mListAnswers.getAdapter())
                        .setSelectedValue(question.getAnswerType());
                onAnswerSelected(question.getAnswerType());
            }
        }
    }

    private void updateButtonViews(Integer index) {
        if (mBtnPrev != null) {
            if (index != 0) {
                mBtnPrev.setEnabled(true);
            } else {
                mBtnPrev.setEnabled(false);
            }
        }

        if (mBtnNext != null) {
            if (index != mViewModel.getQuestions().size() - 1) {
                mBtnNext.setEnabled(true);
            } else {
                mBtnNext.setEnabled(false);
            }
        }

        if (mBtnDone != null) {
            if (index == mViewModel.getQuestions().size() - 1) {
                mBtnDone.setVisibility(View.VISIBLE);
            } else {
                mBtnDone.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnNext) {
            handleNextClicked();
        } else if (view == mBtnPrev) {
            handlePreviousClicked();
        } else if (view == mBtnDone) {
            handleDoneClicked();
        }
    }

    private void handleDoneClicked() {
        LiveData<Boolean> success = mViewModel.insertQuestions();
        success.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean insertResult) {
                if (insertResult) {
                    Timber.i("Questions inserted successfully");
                    goUp();
                }
            }
        });
    }

    private void handleNextClicked() {
        @AnswerType int answerType = AnswerType.NO;
        if (mListAnswers != null) {
            if (mListAnswers.getAdapter() != null) {
                answerType = ((AnswerListPickerAdapter)mListAnswers.getAdapter())
                        .getSelectedValue();
            }
        }

        @DifficultyType int difficultyType = DifficultyType.NA;
        if (mListDifficulties != null) {
            if (mListDifficulties.getAdapter() != null) {
                difficultyType = ((DifficultyListPickerAdapter)mListDifficulties.getAdapter())
                        .getSelectedValue();
            }
        }

        mViewModel.updateQuestion(answerType, difficultyType);
        mViewModel.selectNextQuestion();
    }

    private void handlePreviousClicked() {
        @AnswerType int answerType = AnswerType.NO;
        if (mListAnswers != null) {
            if (mListAnswers.getAdapter() != null) {
                answerType = ((AnswerListPickerAdapter)mListAnswers.getAdapter())
                        .getSelectedValue();
            }
        }

        @DifficultyType int difficultyType = DifficultyType.NA;
        if (mListDifficulties != null) {
            if (mListDifficulties.getAdapter() != null) {
                difficultyType = ((DifficultyListPickerAdapter)mListDifficulties.getAdapter())
                        .getSelectedValue();
            }
        }

        mViewModel.updateQuestion(answerType, difficultyType);
        mViewModel.selectPreviousQuestion();
    }

    private Observer<Integer> mIndexObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer index) {
            Timber.i("Refreshing Question -> index is " + index);
            updateButtonViews(index);
            updateDataViews();
        }
    };
}
