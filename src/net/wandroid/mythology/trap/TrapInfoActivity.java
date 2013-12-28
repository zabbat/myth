package net.wandroid.mythology.trap;

import net.wandroid.mythology.PlayerTrapList;
import net.wandroid.mythology.R;
import net.wandroid.mythology.map.MythologyStartActivity;
import android.app.Activity;
import android.app.AlertDialog;
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

public class TrapInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trap_info_activity);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent intent=getIntent();
		//get extras...
		
		int id=intent.getIntExtra(PlayerTrapList.TRAP_ID_KEY,-1);
		init(id);
	}

	private void init(final int id){
		final Trap trap=PlayerTrapList.getInstance().get(id);
		
		
		((TextView) findViewById(R.id.trap_info_name_text)).setText(trap.getTitle());
		((TextView) findViewById(R.id.trap_info_placed_text)).setText("Is placed:"+trap.isPlaced());
		((ImageView) findViewById(R.id.trap_info_image)).setImageResource(trap.getImageResource());
		
		Button handleBait=(Button) findViewById(R.id.trap_info_set_bait_btn);
		if(trap.getBait()==Bait.NullBait){
			handleBait.setText("Add Bait");
			handleBait.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent();
					intent.setClass(TrapInfoActivity.this, BaitActivity.class);
					startActivity(intent);
				}
			});
		}else{
			handleBait.setText("Remove Bait");
			//add click listener to remove bait
		}
		Button handleTrap=(Button) findViewById(R.id.trap_info_set_trap_btn);
		if(trap.isPlaced()){
			handleTrap.setText("Recall Trap");

			handleTrap.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					trap.remove();
					trap.setIsPlaced(false);
					//reboot ui
					init(id);
				}
			});
			
		}else{
			initPlaceTrap(handleTrap,id,trap);
			};
			
	}

	private void initPlaceTrap(Button handleTrap,final int id,final Trap trap){
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
								intent.putExtra(PlayerTrapList.TRAP_ID_KEY, id);
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
							.setPositiveButton("Yes", yesnoListener)
							.setNegativeButton("No", yesnoListener).create().show();
				}
				
			}
		});
		
	}
	

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		//getActionBar().setDisplayHomeAsUpEnabled(true);

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

}
