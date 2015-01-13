package com.todotask.db.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.List;

import static com.todotask.db.contentprovider.TaskProviderContract.AUTHORITY;

/**
 *
 */
public class TaskTodoContentProvider extends ContentProvider {

	private static final UriMatcher sUriMatcher;

	public static final int ALL_TASKS           = 0x01;
	public static final int SINGLE_TASK         = 0x02;
	public static final int TASKS_BY_PRIORITY   = 0x03;
	public static final int TASKS_BY_DATE       = 0x04;
	public static final int SEARCH_ALL_TASKS    = 0x05;
	public static final int SEARCH_DATE_TASK    = 0x06;


	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, TaskProviderContract.Tables.TASK_TABLE_NAME, ALL_TASKS);
		sUriMatcher.addURI(AUTHORITY, TaskProviderContract.Tables.TASK_TABLE_NAME + "/#", SINGLE_TASK);
		sUriMatcher.addURI(AUTHORITY, TaskProviderContract.Tables.TASK_TABLE_NAME + "/date/*", TASKS_BY_DATE);
		sUriMatcher.addURI(AUTHORITY, TaskProviderContract.Tables.TASK_TABLE_NAME + "/date/priority/*", TASKS_BY_PRIORITY);
		sUriMatcher.addURI(AUTHORITY, TaskProviderContract.Tables.TASK_TABLE_NAME + "/search/*", SEARCH_ALL_TASKS);
		sUriMatcher.addURI(AUTHORITY, TaskProviderContract.Tables.TASK_TABLE_NAME + "/search/date/*", SEARCH_DATE_TASK);
	}

	private TodoTaskDatabase mDatabaseHelper = null;

	@Override
	public boolean onCreate() {
		mDatabaseHelper = new TodoTaskDatabase(getContext());
		return true;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		final SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
		int uriMatch = sUriMatcher.match(uri);

		switch (uriMatch) {

			case TASKS_BY_DATE:
				SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
				String dateSelect = getDateSelection(uri);
				String whereClause = getDateWhereClause() + " AND " + getTaskDoneWhere();

				builder.setTables(TaskProviderContract.Tables.TASK_TABLE_NAME);
				Cursor cursor = builder.query(db, projection, whereClause, getSelectionArgs(dateSelect), null,
						null, null, null);

				Context context = getContext();
				if (null != context) {
					cursor.setNotificationUri(context.getContentResolver(), uri);
				}

				return cursor;
		}
		return null;
	}

	private String[] getSelectionArgs(String dateSelect) {
		return new String[] {dateSelect, TaskProviderContract.UserTasks.TASK_NOT_DONE+""};
	}

	private String getDateWhereClause() {
		return TaskProviderContract.TaskColumns.DATE + "=?";
	}

	private String getDateSelection(Uri uri) {
		final List<String> segments = uri.getPathSegments();
		return segments.get(1);
	}


	@Override
	public String getType(Uri uri) {

		int match = sUriMatcher.match(uri);

		switch (match) {
			case ALL_TASKS:
			case TASKS_BY_PRIORITY:
			case TASKS_BY_DATE:
			case SEARCH_ALL_TASKS:
			case SEARCH_DATE_TASK:
				return TaskProviderContract.UserTasks.FULL_CONTENT_TYPE;
			case SINGLE_TASK:
				return TaskProviderContract.UserTasks.SINGLE_ITEM_TYPE;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		return null;
	}


	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}


	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}


	public String getTaskDoneWhere() {
		return TaskProviderContract.TaskColumns.TASK_DONE + "=?";
	}
}
