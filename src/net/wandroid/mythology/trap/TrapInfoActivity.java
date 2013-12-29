package net.wandroid.mythology.trap;

import net.wandroid.mythology.PlayerBaitMap;
import net.wandroid.mythology.PlayerTrapList;
import net.wandroid.mythology.R;
import net.wandroid.mythology.bait.Bait;
import net.wandroid.mythology.bait.BaitSelectFragment;
import net.wandroid.mythology.bait.BaitSelectFragment.BaitSelectedListener;
import net.wandroid.mythology.map.MythologyStartActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TrapInfoActivity extends Activity implements BaitSelectedListener{

	private Trap mTrap = Trap.NullTrap;
	private int mTrapId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trap_info_activity);
		// Show the Up button in the action bar.
		setupActionBar();

		Intent intent = getIntent();
		// get extras...

		mTrapId = intent.getIntExtra(PlayerTrapList.TRAP_ID_KEY, -1);
		mTrap = PlayerTrapList.getInstance().get(mTrapId);
		init();
	}

	private void init() {

		((TextView) findViewById(R.id.trap_info_name_text)).setText(mTrap
				.getTitle());
		((TextView) findViewById(R.id.trap_info_placed_text))
				.setText("Is placed:" + mTrap.isPlaced());
		((ImageView) findViewById(R.id.trap_info_image)).setImageResource(mTrap
				.getImageResource());

		Button handleTrap = (Button) findViewById(R.id.trap_info_set_trap_btn);
		if (mTrap.isPlaced()) {
			handleTrap.setText("Recall Trap");

			handleTrap.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mTrap.remove();
					mTrap.setIsPlaced(false);
					// reboot ui
					init();
				}
			});

		} else {
			initPlaceTrap(handleTrap, mTrap);
		}
		
		BaitSelectFragment baitSelectFrag1=(BaitSelectFragment) getFragmentManager().findFragmentById(R.id.trap_info_bait_add_1_frag);
		baitSelectFrag1.setBait(mTrap.getBait(0));
		baitSelectFrag1.setIsTrapPlaced(mTrap.isPlaced());
		
		BaitSelectFragment baitSelectFrag2=(BaitSelectFragment) getFragmentManager().findFragmentById(R.id.trap_info_bait_add_2_frag);
		baitSelectFrag2.setBait(mTrap.getBait(1));
		baitSelectFrag2.setIsTrapPlaced(mTrap.isPlaced());
	}

	private void initPlaceTrap(Button handleTrap, final Trap trap) {
		handleTrap.setText("Place Trap");
		handleTrap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (!trap.hasBait()) {
					DialogInterface.OnClickListener yesnoListener = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								Intent intent = new Intent();
								intent.putExtra(
										MythologyStartActivity.MAP_MODE.MAP_MODE_TYPE_KEY,
										MythologyStartActivity.MAP_MODE.MAP_PLACE_TRAP);
								intent.putExtra(PlayerTrapList.TRAP_ID_KEY,
										mTrapId);
								intent.setClass(getApplicationContext(),
										MythologyStartActivity.class);
								startActivity(intent);
								dialog.dismiss();
								finish();
								break;
							case DialogInterface.BUTTON_NEGATIVE:
								dialog.dismiss();
								break;
							}
						}
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(
							TrapInfoActivity.this);
					builder.setMessage("There is no Bait in Trap. Continue?")
							.setNegativeButton("No", yesnoListener)
							.setPositiveButton("Yes", yesnoListener).create()
							.show();
				}else{
					Intent intent = new Intent();
					intent.putExtra(
							MythologyStartActivity.MAP_MODE.MAP_MODE_TYPE_KEY,
							MythologyStartActivity.MAP_MODE.MAP_PLACE_TRAP);
					intent.putExtra(PlayerTrapList.TRAP_ID_KEY,
							mTrapId);
					intent.setClass(getApplicationContext(),
							MythologyStartActivity.class);
					startActivity(intent);
				}

			}
		});

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		// getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trap_info, menu);
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
	public void onBaitSelected(Bait bait, Fragment sender) {
		
		int baitIndex=-1;
		
		if(sender==getFragmentManager().findFragmentById(R.id.trap_info_bait_add_1_frag)){
			baitIndex=0;
		}
		if(sender==getFragmentManager().findFragmentById(R.id.trap_info_bait_add_2_frag)){
			baitIndex=1;
		}

		//if removing bait, it should go back to players stash
		if(mTrap.getBait(baitIndex)!=Bait.NullBait && bait==Bait.NullBait){
			PlayerBaitMap.getInstance().add(mTrap.getBait(baitIndex));
		}
		mTrap.setBait(bait, baitIndex);
	}


}
