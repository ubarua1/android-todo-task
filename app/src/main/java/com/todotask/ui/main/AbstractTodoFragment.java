package com.todotask.ui.main;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.todotask.db.contentprovider.TaskProviderContract;
import com.todotask.db.model.TaskItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public abstract class AbstractTodoFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

	public OnFragmentInteractionListener mListener;

	/**
	 * The cursor object obtained from the loader
	 */
	private Cursor mCursor;

	/**
	 *
	 */
	protected long mToday;

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
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;

		getLoaderManager().destroyLoader(TasksQuery.NORMAL_LOADER_TOKEN);
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
		public void onFragmentInteraction(TaskItem id);

		public void setToolbarTitle(String title);
	}

	/**
	 * Called once the loader has finished fetching the data.
	 * @param data cursor pointing to the data.
	 */
	protected abstract void onDataLoaded(Cursor data);

	private interface TasksQuery {

		int NORMAL_LOADER_TOKEN = 0x1;

		String NORMAL_SORT_ORDER = TaskProviderContract.TaskColumns._ID + " desc ";

		String[] NORMAL_PROJECTION = new String[] {
				TaskProviderContract.TaskColumns._ID,
				TaskProviderContract.TaskColumns.TITLE,
				TaskProviderContract.TaskColumns.DESCRIPTION,
		};

	}

}
