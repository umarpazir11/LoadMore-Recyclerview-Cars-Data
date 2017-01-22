package com.auto1_test.api_aws_eu_qa_1.auto1codingtest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments.ManufactureFragment.OnListFragmentInteractionListener;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.R;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.ManufacturerData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.utils.OnLoadMoreListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ManufacturerData} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ManufactureRecyclerViewAdapter extends RecyclerView.Adapter<ManufactureRecyclerViewAdapter.ViewHolder> {

  //  private final List<DummyItem> mValues;
    private List<ManufacturerData> mManufacturerDataList;
    private final OnListFragmentInteractionListener mListener;

    public ManufactureRecyclerViewAdapter(List<ManufacturerData> items, OnListFragmentInteractionListener listener) {
        this.mManufacturerDataList =items;
        //mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
               view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.manufacture_fragment_item, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.manufacture_fragment_item_odd, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.mItem = mManufacturerDataList.get(position);
        holder.mContentView.setText(mManufacturerDataList.get(position).mManufacturerName);

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
    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }
    @Override
    public int getItemCount() {
        return mManufacturerDataList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public ManufacturerData mItem;

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
