package com.todotask.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.todotask.db.model.TaskItem;

import java.util.ArrayList;

/**
 *
 */
public class TaskListAdapter implements ListAdapter {

	/**
	 *
	 */
	private final ArrayList<TaskItem> mItems;


	private final Context mContext;

	public TaskListAdapter(Context context, ArrayList<TaskItem> items) {
		mItems = items;
		mContext = context;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {

	}

	@Override
	public int getCount() {

		return 31;
	}

	@Override
	public Object getItem(int position) {
		return position >= 0 && position < mItems.size() ? mItems.get(position) : null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		return null;
	}

	@Override
	public int getItemViewType(int position) {
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return mItems.isEmpty();
	}
}
