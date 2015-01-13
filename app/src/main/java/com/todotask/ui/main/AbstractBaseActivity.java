package com.todotask.ui.main;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.todotask.R;

/**
 * Base activity class for all the activities in this app.
 * Sets up the toolbar and other stuff
 */
public abstract class AbstractBaseActivity extends ActionBarActivity {

	private Toolbar mActionBarToolbar = null;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		setUpActionBarToolbar();

		onPostSetContentView();
	}

	private void setUpActionBarToolbar() {
		if (mActionBarToolbar == null) {
			mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
			if (mActionBarToolbar != null) {
				setSupportActionBar(mActionBarToolbar);
				getSupportActionBar().setDisplayShowTitleEnabled(false);
				mActionBarToolbar.bringToFront();
				getSupportActionBar().setDisplayShowHomeEnabled(true);
			}
		}
	}

	public Toolbar getToolbar() {
		return mActionBarToolbar;
	}

	protected abstract void onPostSetContentView();

	protected abstract int getLayoutId();
}
