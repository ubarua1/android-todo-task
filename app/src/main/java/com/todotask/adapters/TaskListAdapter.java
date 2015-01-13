package com.todotask.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 *
 */
public class TaskListAdapter extends CursorAdapter {


	public TaskListAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, autoRequery);
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
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return null;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

	}


	@Override
	public int getItemViewType(int position) {
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

}
