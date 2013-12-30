package net.wandroid.mythology.bait;

import net.wandroid.mythology.PlayerBaitMap;
import net.wandroid.mythology.R;
import net.wandroid.mythology.bait.BaitPlayerListFragment.BaitListListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class BaitPlayerActivity extends Activity implements BaitListListener{

	public static final String BAIT_ACTIVITY_START_AS_PICKER = "BaitActivity_start_as_picker";	

	private boolean mIsPicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bait_activity);
		// Show the Up button in the action bar.
		setupActionBar();
		Intent intent=getIntent();
		mIsPicker=intent.hasExtra(BAIT_ACTIVITY_START_AS_PICKER);
		((BaitPlayerListFragment)getFragmentManager().findFragmentById(R.id.bait_list_frag)).setIsPickerList(mIsPicker);
		
		View createBaitView=findViewById(R.id.bait_list_create_btn);
		createBaitView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(BaitPlayerActivity.this, BaitShopActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bait, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onListItemClicked(Bait bait) {
		if(mIsPicker){
			Intent intent=new Intent();
			intent.putExtra(Bait.BAIT_NAME_KEY, bait.getName());
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	}

}
