package com.auto1_test.api_aws_eu_qa_1.auto1codingtest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.YearData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments.YearFragment.OnListFragmentInteractionListener;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link YearData} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class YearRecyclerViewAdapter extends RecyclerView.Adapter<YearRecyclerViewAdapter.ViewHolder> {

    private final List<YearData> mYearValues;
    private final OnListFragmentInteractionListener mListener;

    public YearRecyclerViewAdapter(List<YearData> items, OnListFragmentInteractionListener listener) {
        mYearValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_year, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mYearValues.get(position);
        holder.mContentView.setText(mYearValues.get(position).mYear);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mYearValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public YearData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
