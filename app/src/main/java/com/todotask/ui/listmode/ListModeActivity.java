package com.todotask.ui.listmode;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.todotask.R;
import com.todotask.db.model.TaskItem;
import com.todotask.ui.calendarmode.CalendarModeActivity;
import com.todotask.ui.main.AbstractBaseActivity;
import com.todotask.ui.main.AbstractTodoFragment;
import com.todotask.ui.utils.UiUtils;
import com.todotask.ui.widgets.SlidingTabLayout;

import java.util.Calendar;
import java.util.Date;


public class ListModeActivity extends AbstractBaseActivity implements AbstractTodoFragment.OnFragmentInteractionListener {

	// Widget references
	private ViewPager mPager;
	private SlidingTabLayout mSlidingLayout;
	private TextView mToolbarTitle;

	@Override
	protected void onPostSetContentView() {

		// Get the view pager
		mPager = (ViewPager) findViewById(R.id.pager_tasks);
		mPager.setAdapter(new TasksPagerAdapter(getFragmentManager()));
		mPager.setCurrentItem(getTodaysDayOfMonth(), true);
		mPager.setOnPageChangeListener(new PageChangeListener());

		mToolbarTitle = (TextView) getToolbar().findViewById(R.id.toolbar_content_text);
		setToolbarTitle(UiUtils.formatToFullDate(new Date().getTime()));
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_home_screen;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_home_screen, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
			case R.id.action_settings:
				return true;

			case R.id.action_calendar_mode_menu:
				goToCalendarMode();
		}

		return super.onOptionsItemSelected(item);
	}

	private void goToCalendarMode() {

		Intent intent = new Intent(this, CalendarModeActivity.class);
		startActivity(intent);

	}

	@Override
	public void onFragmentInteraction(TaskItem id) {

	}

	@Override
	public void setToolbarTitle(String title) {
		mToolbarTitle.setText(title);
	}

	public int getTodaysDayOfMonth() {

		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);

		// View pager indices start from 0
		return day - 1;
	}

	private class TasksPagerAdapter extends FragmentPagerAdapter {
		public TasksPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		private final Calendar CALENDAR = Calendar.getInstance();

		@Override
		public Fragment getItem(int position) {

			if(position == getTodaysDayOfMonth()) {
				return TodoTasksListFragment.newInstance(new Date().getTime());
			}
			else {
				// We need to find the difference
				int difference = position - getTodaysDayOfMonth();
				CALENDAR.add(Calendar.DAY_OF_MONTH, difference);

				// Now that the difference has been added/subtracted
				// Pass that day's miliseconds to the fragment
				return TodoTasksListFragment.newInstance(CALENDAR.getTimeInMillis());
			}
		}

		/**
		 * Return the number of views available.
		 */
		@Override
		public int getCount() {
			return CALENDAR.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return super.getPageTitle(position);
		}
	}

	private class PageChangeListener extends ViewPager.SimpleOnPageChangeListener {

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			Log.d("", "position="+position + " positionOffset="+positionOffset + " positionOffsetPixels="+positionOffsetPixels);
		}

		@Override
		public void onPageSelected(int position) {

			String title;

			if(position == getTodaysDayOfMonth()) {
				title = UiUtils.formatToFullDate(new Date().getTime());
			}
			else {
				// We need to find the difference
				int difference = position - getTodaysDayOfMonth();
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_MONTH, difference);

				title = UiUtils.formatToFullDate(c.getTimeInMillis());
			}
			setToolbarTitle(title);
		}
	}
}
