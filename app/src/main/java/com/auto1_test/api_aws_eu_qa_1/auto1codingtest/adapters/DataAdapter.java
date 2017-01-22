package com.auto1_test.api_aws_eu_qa_1.auto1codingtest.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.fragments.ManufactureFragment.OnListFragmentInteractionListener;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.R;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.data.ManufacturerData;
import com.auto1_test.api_aws_eu_qa_1.auto1codingtest.utils.OnLoadMoreListener;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter {
	private final int VIEW_ITEM = 1;
	private final int VIEW_PROG = 0;

	private List<ManufacturerData> studentList;

	// The minimum amount of items to have below your current scroll position
	// before loading more.
	private int visibleThreshold = 5;
	private int lastVisibleItem, totalItemCount;
	private boolean loading;
	private OnLoadMoreListener onLoadMoreListener;
	private static OnListFragmentInteractionListener mListener;

	/**
	 * {@link RecyclerView.Adapter} that can display a {@link ManufacturerData} and makes a call to the
	 * specified {@link OnListFragmentInteractionListener}.
	 * TODO: Replace the implementation with code for your data type.
	 */
	public DataAdapter(List<ManufacturerData> students, RecyclerView recyclerView, OnListFragmentInteractionListener listener) {
		studentList = students;
		this.mListener =listener;

		if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
			final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
					recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
						@Override
						public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
							super.onScrolled(recyclerView, dx, dy);
							totalItemCount = linearLayoutManager.getItemCount();
							lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
							if (! loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
								if (onLoadMoreListener != null) {
									onLoadMoreListener.onLoadMore();
								}
								loading = true;
							}
						}
					});
		}
	}

	@Override
	public int getItemViewType(int position) {

            return studentList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RecyclerView.ViewHolder vh;
		if (viewType == VIEW_ITEM) {
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.manufacture_fragment_item, parent, false);
			vh = new StudentViewHolder(v);
		} else {
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
			vh = new ProgressViewHolder(v);
		}
		return vh;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof StudentViewHolder) {
			ManufacturerData singleStudent=studentList.get(position);
			((StudentViewHolder) holder).tvName.setText(singleStudent.mManufacturerName);
			((StudentViewHolder) holder).student= singleStudent;
		} else {
			((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
		}
	}

	public void setLoaded(boolean state) {
		loading = state;
	}

	@Override
	public int getItemCount() {
		return studentList.size();
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		this.onLoadMoreListener = onLoadMoreListener;
	}


	//
	public static class StudentViewHolder extends RecyclerView.ViewHolder {
		public TextView tvName;
		
		public TextView tvEmailId;
		
		public ManufacturerData student;

		public StudentViewHolder(View v) {
			super(v);
			tvName = (TextView) v.findViewById(R.id.content);


			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (null != mListener) {
						// Notify the active callbacks interface (the activity, if the
						// fragment is attached to one) that an item has been selected.
						mListener.onListFragmentInteraction(student);
					}

				}
			});
		}
	}

	public static class ProgressViewHolder extends RecyclerView.ViewHolder {
		public ProgressBar progressBar;

		public ProgressViewHolder(View v) {
			super(v);
			progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
		}
	}
}