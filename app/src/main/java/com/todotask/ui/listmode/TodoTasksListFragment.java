package com.todotask.ui.listmode;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.todotask.adapters.TaskListAdapter;
import com.todotask.db.model.TaskItem;
import com.todotask.ui.listmode.dummy.DummyContent;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TodoTasksListFragment extends AbstractTodoFragment {

	private OnFragmentInteractionListener mListener;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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

		mListOfTaskItems = new ArrayList<>();
		mAdapter = new TaskListAdapter(activity, mListOfTaskItems);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
		mAdapter = null;
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		if (null != mListener) {
			// Notify the active callbacks interface (the activity, if the
			// fragment is attached to one) that an item has been selected.
			mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
		}
	}

	@Override
	protected void onDataLoaded(Cursor data) {

		ArrayList<TaskItem> newItems = getTaskItems(data);

	}

	private ArrayList<TaskItem> getTaskItems(Cursor data) {
		return null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p/>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {

		// TODO: Update argument type and name
		public void onFragmentInteraction(String id);
	}

}
