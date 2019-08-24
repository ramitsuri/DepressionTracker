package com.ramitsuri.depressiontracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.utils.DateHelper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    @Nullable
    private List<Long> mDates;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtDate;

        ViewHolder(View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.text_date);
        }

        private void bind(final Long date) {
            txtDate.setText(DateHelper.getFriendlyDate(date));
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void setHistory(List<Long> dates) {
        if (mDates != null) {
            if (mDates != dates) {
                mDates = dates;
                notifyDataSetChanged();
            }
        } else {
            // first initialization
            mDates = dates;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mDates != null) {
            Long date = mDates.get(position);
            holder.bind(date);
        }
    }

    @Override
    public int getItemCount() {
        if (mDates == null) {
            return 0;
        }
        return mDates.size();
    }
}
