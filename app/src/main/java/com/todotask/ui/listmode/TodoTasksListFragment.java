package com.todotask.ui.listmode;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.todotask.adapters.TaskListAdapter;
import com.todotask.db.model.TaskItem;
import com.todotask.ui.main.AbstractTodoFragment;

import java.util.ArrayList;

import static com.todotask.common.Constants.TODAYS_DATE_MILL_BUNDLE;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TodoTasksListFragment extends AbstractTodoFragment {

	private ArrayList<TaskItem> mListOfTaskItems;

	/**
	 * List view adapter
	 */
	private TaskListAdapter mAdapter;


	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public TodoTasksListFragment() {
	}

	public static TodoTasksListFragment newInstance(long todayMilBundle) {

		TodoTasksListFragment fragment = new TodoTasksListFragment();
		Bundle b = new Bundle();
		b.putLong(TODAYS_DATE_MILL_BUNDLE, todayMilBundle);
		fragment.setArguments(b);

		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}

		Bundle bundle = getArguments();
		if(bundle == null) {
			throw new IllegalArgumentException("Bundle must not be null");
		}

		mToday = bundle.getLong(TODAYS_DATE_MILL_BUNDLE);
		mAdapter = new TaskListAdapter(activity, null, false);

	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
		mAdapter = null;
	}

	@Override
	public void onResume() {
		super.onResume();
		//mListener.setToolbarTitle(UiUtils.formatToFullDate(mToday));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		if (null != mListener) {
			// Notify the active callbacks interface (the activity, if the
			// fragment is attached to one) that an item has been selected.
			mListener.onFragmentInteraction((TaskItem)l.getItemAtPosition(position));
		}
	}

	@Override
	protected void onDataLoaded(Cursor data) {

		mAdapter.swapCursor(data);
		setListAdapter(mAdapter);
		setEmptyText("No data");

	}
}
