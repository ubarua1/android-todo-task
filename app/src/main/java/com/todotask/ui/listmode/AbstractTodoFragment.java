package com.todotask.ui.listmode;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.todotask.db.contentprovider.TaskProviderContract;
import com.todotask.ui.listmode.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public abstract class AbstractTodoFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	private OnFragmentInteractionListener mListener;

	/**
	 * The cursor object obtained from the loader
	 */
	private Cursor mCursor;

	/**
	 *
	 */
	private long mToday;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public AbstractTodoFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initializing the loader
		getLoaderManager().initLoader(TasksQuery.NORMAL_LOADER_TOKEN, null, this);

		// TODO: Change Adapter to display your content
		setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
				android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS));
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

		mToday = System.currentTimeMillis();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;

		getLoaderManager().destroyLoader(TasksQuery.NORMAL_LOADER_TOKEN);
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
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {

		Uri contentUri;
		Loader<Cursor> cursorLoader = null;

		if(id == TasksQuery.NORMAL_LOADER_TOKEN) {
			contentUri = TaskProviderContract.UserTasks.buildUriForDate(mToday);

			cursorLoader = new CursorLoader(getActivity(),contentUri, TasksQuery.NORMAL_PROJECTION,
								TaskProviderContract.UserTasks.DATE + "=?",
								new String[] { Long.toString(mToday) },
								TasksQuery.NORMAL_SORT_ORDER );
		}

		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

		int id = loader.getId();

		if(id == TasksQuery.NORMAL_LOADER_TOKEN) {

			if(mCursor != null && mCursor != data) {
				mCursor.close();
			}
			else {

				mCursor = data;

				onDataLoaded(data);
			}
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {

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

	/**
	 * Called once the loader has finished fetching the data.
	 * @param data cursor pointing to the data.
	 */
	protected abstract void onDataLoaded(Cursor data);

	private interface TasksQuery {

		int NORMAL_LOADER_TOKEN = 0x1;

		String NORMAL_SORT_ORDER = TaskProviderContract.TaskColumns.TASKS_ID + "desc";

		String[] NORMAL_PROJECTION = new String[] {
				TaskProviderContract.TaskColumns.TASKS_ID,
				TaskProviderContract.TaskColumns.TITLE,
				TaskProviderContract.TaskColumns.DESCRIPTION,
				TaskProviderContract.TaskColumns.TASK_DONE
		};

	}

}
