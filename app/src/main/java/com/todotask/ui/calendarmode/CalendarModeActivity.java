package com.todotask.ui.calendarmode;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.roomorama.caldroid.CaldroidFragment;
import com.todotask.R;
import com.todotask.ui.main.AbstractBaseActivity;

import java.util.Calendar;

public class CalendarModeActivity extends AbstractBaseActivity {

	private CaldroidFragment caldroidFragment;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_calendar_mode, menu);
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

	@Override
	protected void onPostSetContentView() {

		caldroidFragment = new CaldroidFragment();
		Calendar cal = Calendar.getInstance();
		Bundle args = new Bundle();
		args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
		args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
		args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
		args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
		caldroidFragment.setArguments(args);


		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar_replace, caldroidFragment);
		t.commit();

	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_calendar_mode;
	}
}
