package com.ramitsuri.depressiontracker.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import timber.log.Timber;

public class BaseFragment extends Fragment {

    void showActionBar() {
        Activity activity = getActivity();
        ActionBar actionBar = null;
        if (activity != null) {
            actionBar = ((AppCompatActivity)activity).getSupportActionBar();
        }
        if (actionBar != null) {
            actionBar.show();
        }
    }

    void hideActionBar() {
        Activity activity = getActivity();
        ActionBar actionBar = null;
        if (activity != null) {
            actionBar = ((AppCompatActivity)activity).getSupportActionBar();
        }
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Timber.i("%s OnAttach", this.getClass().getSimpleName());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("%s OnCreate", this.getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        Timber.i("%s OnCreateView", this.getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.i("%s OnViewCreated", this.getClass().getSimpleName());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Timber.i("%s OnViewStateRestored", this.getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.i("%s OnAttach", this.getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.i("%s OnResume", this.getClass().getSimpleName());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.i("%s OnSaveInstanceState", this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.i("%s OnPause", this.getClass().getSimpleName());
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.i("%s OnStop", this.getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.i("%s OnDestroyView", this.getClass().getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("%s OnDestroy", this.getClass().getSimpleName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Timber.i("%s OnDetach", this.getClass().getSimpleName());
    }
}
