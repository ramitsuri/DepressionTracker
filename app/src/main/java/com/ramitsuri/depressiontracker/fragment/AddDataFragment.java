package com.ramitsuri.depressiontracker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramitsuri.depressiontracker.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddDataFragment extends BaseFragment {
    public AddDataFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        hideActionBar();
    }

    @Override
    public void onStop() {
        super.onStop();
        showActionBar();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*mViewModel = ViewModelProviders.of(this).get(AddExpenseViewModel.class);

        setupViews(view);

        setupRecyclerViews(view);*/
    }
}
