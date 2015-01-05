package com.todotask.ui.listmode;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.todotask.R;
import com.todotask.ui.main.AbstractBaseActivity;
import com.todotask.ui.widgets.SlidingTabLayout;


public class ListModeActivity extends AbstractBaseActivity {

	// Widget references
	private ViewPager mPager;
	private SlidingTabLayout mSlidingLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.activity_home_screen);

		// Get the view pager
		mPager = (ViewPager) findViewById(R.id.pager_tasks);
		mPager.setAdapter(new TasksPagerAdapter(getFragmentManager()));

		// Get the sliding tab layout and attach it to the view pager
		mSlidingLayout = (SlidingTabLayout) findViewById(R.id.slidingTab);
		mSlidingLayout.setViewPager(mPager);



		super.onCreate(savedInstanceState);
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

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private class TasksPagerAdapter extends FragmentPagerAdapter {
		public TasksPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int i) {
			return null;
		}

		/**
		 * Return the number of views available.
		 */
		@Override
		public int getCount() {
			return 0;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return super.getPageTitle(position);
		}
	}
}
