package com.todotask.ui.main;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.todotask.R;

/**
 * Created by Udayaditya on 29-Dec-14.
 */
public class AbstractBaseActivity extends ActionBarActivity {


	private Toolbar mActionBarToolbar = null;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		getActionBarToolbar();
		super.onCreate(savedInstanceState);

	}

	protected Toolbar getActionBarToolbar() {
		if (mActionBarToolbar == null) {
			mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
			if (mActionBarToolbar != null) {
				setSupportActionBar(mActionBarToolbar);
			}
		}
		return mActionBarToolbar;
	}
}
