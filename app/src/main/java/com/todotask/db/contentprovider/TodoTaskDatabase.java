package com.todotask.db.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class TodoTaskDatabase extends SQLiteOpenHelper {

	protected static final String TASKS_TABLE_TBL_SQL = "CREATE TABLE" + " " + TaskProviderContract.Tables.TASK_TABLE_NAME +  " ( " + TaskProviderContract.ROW_ID + " " + "INTEGER"
			+ " " + "PRIMARY KEY" + " " + "AUTOINCREMENT" +
			"," + TaskProviderContract.TaskColumns.TITLE + " " + "TEXT" +
			"," + TaskProviderContract.TaskColumns.DESCRIPTION + " " + "TEXT" +
			"," + TaskProviderContract.TaskColumns.DATE + " " + "TEXT" +
			"," + TaskProviderContract.TaskColumns.PRIORITY + " " + "INTEGER" +
			"," + TaskProviderContract.TaskColumns.TASK_DONE + " " + "INTEGER" + ")";

	private static final String DATABASE_NAME = "todo_tasks.db";

	private static final int VERSION = 1;

	public TodoTaskDatabase(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TASKS_TABLE_TBL_SQL);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
