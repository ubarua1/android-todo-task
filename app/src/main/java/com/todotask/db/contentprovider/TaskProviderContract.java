package com.todotask.db.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

import static com.todotask.common.Constants.CP_CURSOR_SINGLE;
import static com.todotask.common.Constants.CP_CURSOR_WHOLE;

/**
 *
 */
public class TaskProviderContract {

	protected TaskProviderContract() {}

	// Content provider constants
	protected static final String AUTHORITY = "com.todotask";
	protected static final String SCHEME = "content";
	protected static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

	// Constants for building SQLite tables during initialization
	protected static final String	TEXT_TYPE					= "TEXT";
	protected static final String	INTEGER_TYPE				= "INTEGER";
	protected static final String	PRIMARY_KEY_TYPE			= "PRIMARY KEY";
	protected static final String	AUTO_INCREMENT				= "AUTOINCREMENT";
	protected static final String	FOREIGN_KEY_TYPE			= "FOREIGN KEY";
	protected static final String	FOREIGN_REFERENCE_TYPE		= "REFERENCES";

	/**
	 * Generic android ID used for list views
	 */
	public static final String	ROW_ID						    = BaseColumns._ID;

	public static final String PATH_TASKS                       = Tables.TASK_TABLE_NAME;

	public static final String PATH_SEARCH                      = "search";
	public static final String PATH_DATE                        = "date";


	public static interface TaskColumns extends BaseColumns {
		// user_tasks colummns
		public static final String TASKS_ID         = "task_id";
		public static final String TITLE            = "t_title";
		public static final String DESCRIPTION      = "t_description";
		public static final String DATE             = "t_date";
		public static final String PRIORITY         = "t_priority";
		public static final String TASK_DONE        = "t_done";
	}

	public static interface Tables {
		public static final String TASK_TABLE_NAME = "user_tasks";
	}

	public static class UserTasks implements TaskColumns, BaseColumns {

		public static final Uri CONTENT_URI =
				BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

		public static final Uri CONTENT_URI_DATE =
				CONTENT_URI.buildUpon().appendPath(PATH_DATE).build();

		public static final String FULL_CONTENT_TYPE    = CP_CURSOR_WHOLE + "vnd.todotasks.usertasks";

		public static final String SINGLE_ITEM_TYPE     = CP_CURSOR_SINGLE + "vnd.todotasks.usertasks";

		public static final int TASK_DONE = 0x101;

		public static final int TASK_NOT_DONE = 0x102;

		public static Uri buildUriForDate(long milliseconds) {
			String date = String.valueOf(milliseconds);
			return CONTENT_URI_DATE.buildUpon().appendPath(date).build();
		}

		public static Uri buildUriForPriority(int priority) {
			String priorityString = String.valueOf(priority);
			return CONTENT_URI_DATE.buildUpon().appendPath(priorityString).build();
		}

		/**
		 * Build {@link Uri} that references that match the query. The query can be
		 * multiple words separated with spaces.
		 *
		 * @param query The query. Can be multiple words separated by spaces.
		 * @return {@link Uri} to the sessions
		 */
		public static Uri buildSearchUri(String query) {
			if (null == query) {
				query = "";
			}
			// convert "lorem ipsum dolor sit" to "lorem* ipsum* dolor* sit*"
			query = query.replaceAll(" +", " *") + "*";
			return CONTENT_URI.buildUpon()
					.appendPath(PATH_SEARCH).appendPath(query).build();
		}
	}


}
