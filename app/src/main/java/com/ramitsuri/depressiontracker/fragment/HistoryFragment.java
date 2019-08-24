package com.ramitsuri.depressiontracker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.adapter.HistoryAdapter;
import com.ramitsuri.depressiontracker.viewModel.HistoryViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class HistoryFragment extends BaseFragment {
    private HistoryViewModel mViewModel;
    private HistoryAdapter mAdapter;

    public HistoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HistoryFragment.this)
                        .navigate(R.id.nav_action_add_data, null);
            }
        });

        setupList(view);
    }

    private void setupList(View view) {
        if (mAdapter == null) {
            mAdapter = new HistoryAdapter();
        }

        RecyclerView listExpenses = view.findViewById(R.id.list_dates);
        listExpenses.setLayoutManager(new LinearLayoutManager(getActivity()));
        listExpenses.setAdapter(mAdapter);

        // NOTE: Observer is triggered multiple times because of
        // 1. lifecycle state changing from inactive to active and
        // 2. data update
        mViewModel.getDates().observe(getViewLifecycleOwner(), mObserver);
    }

    private Observer<List<Long>> mObserver = new Observer<List<Long>>() {
        @Override
        public void onChanged(List<Long> dates) {
            Timber.i("Refreshing expenses");
            if (mAdapter != null) {
                mAdapter.setHistory(dates);
            }
        }
    };
}
