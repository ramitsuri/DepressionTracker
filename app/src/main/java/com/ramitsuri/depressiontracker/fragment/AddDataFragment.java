package com.ramitsuri.depressiontracker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.entities.Question;
import com.ramitsuri.depressiontracker.viewModel.AddDataViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import timber.log.Timber;

public class AddDataFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mBtnPrev, mBtnNext;
    private TextView mTextQuestion;
    private ChipGroup mListAnswers, mListDifficulties;

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

        mListAnswers = view.findViewById(R.id.list_answers);
        mListAnswers.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId == R.id.answer_no) {
                    Timber.i("no");
                } else if (checkedId == R.id.answer_yes) {
                    Timber.i("yes");
                }
            }
        });

        mListDifficulties = view.findViewById(R.id.list_difficulties);
        mListDifficulties.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId == R.id.difficulty_not_at_all) {
                    Timber.i("not at all");
                } else if (checkedId == R.id.difficulty_somewhat) {
                    Timber.i("somewhat");
                } else if (checkedId == R.id.difficulty_very) {
                    Timber.i("very");
                } else if (checkedId == R.id.difficulty_extremely) {
                    Timber.i("extremely");
                }
            }
        });
    }

    private void updateData() {
        Question question = mViewModel.getSelectedQuestion();
        if (question != null && mTextQuestion != null) {
            mTextQuestion.setText(question.getText());
        }
    }

    private void updateViews(Integer index) {
        if (mBtnPrev == null) {
            return;
        }
        if (index != 0) {
            mBtnPrev.setEnabled(true);
        } else {
            mBtnPrev.setEnabled(false);
        }
        if (mBtnNext == null) {
            return;
        }
        if (index != mViewModel.getQuestions().size() - 1) {
            mBtnNext.setEnabled(true);
        } else {
            mBtnNext.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnNext) {
            handleNextClicked();
        } else if (view == mBtnPrev) {
            handlePreviousClicked();
        }
    }

    private void handleNextClicked() {
        mViewModel.selectNextQuestion();
    }

    private void handlePreviousClicked() {
        mViewModel.selectPreviousQuestion();
    }

    private Observer<Integer> mIndexObserver = new Observer<Integer>() {
        @Override
        public void onChanged(Integer index) {
            Timber.i("Refreshing Question -> index is " + index);
            updateViews(index);
            updateData();
        }
    };
}
