package com.ramitsuri.depressiontracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import com.ramitsuri.depressiontracker.R;
import com.ramitsuri.depressiontracker.intdef.DifficultyType;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class DifficultyListPickerAdapter
        extends RecyclerView.Adapter<DifficultyListPickerAdapter.ViewHolder> {

    @Nullable
    private List<Integer> mValues;
    @Nullable
    private ListPickerAdapterCallback mCallback;
    private int mSelectedValue;

    public interface ListPickerAdapterCallback {
        void onItemPicked(@DifficultyType Integer value);
    }

    public DifficultyListPickerAdapter() {
        mSelectedValue = DifficultyType.NA;
    }

    public void setValues(@NonNull List<Integer> values) {
        mValues = values;
        notifyDataSetChanged();
    }

    public void setSelectedValue(@DifficultyType int selectedValue) {
        mSelectedValue = selectedValue;
        notifyDataSetChanged();
    }

    @DifficultyType
    public int getSelectedValue() {
        return mSelectedValue;
    }

    public void setCallback(@NonNull ListPickerAdapterCallback callback) {
        mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_picker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mValues != null) {
            holder.bind(mValues.get(position));
        } else {
            Timber.w("mValues is null");
        }
    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        } else {
            Timber.w("mValues is null");
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Chip txtValue;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtValue = itemView.findViewById(R.id.value);
            txtValue.setOnClickListener(this);
        }

        private void bind(final Integer value) {
            switch (value) {
                case DifficultyType.NOT_AT_ALL:
                    txtValue.setText(R.string.difficulty_not_at_all);
                    break;

                case DifficultyType.SOMEWHAT:
                    txtValue.setText(R.string.difficulty_somewhat);
                    break;

                case DifficultyType.VERY:
                    txtValue.setText(R.string.difficulty_very);
                    break;

                case DifficultyType.EXTREMELY:
                    txtValue.setText(R.string.difficulty_extremely);
                    break;
            }
            if (mValues != null) {
                txtValue.setChecked(mSelectedValue == mValues.get(getAdapterPosition()));
            }
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                if (mValues != null) {
                    mSelectedValue = mValues.get(getAdapterPosition());
                }
                if (mCallback != null) {
                    if (mValues != null) {
                        mCallback.onItemPicked(mValues.get(getAdapterPosition()));
                    } else {
                        Timber.w("mValues is null");
                    }
                } else {
                    Timber.w("mCallback is null");
                }
                notifyDataSetChanged();
            } else {
                Timber.w("getAdapterPosition returned -1");
            }
        }
    }
}
