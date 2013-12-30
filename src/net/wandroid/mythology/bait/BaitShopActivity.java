package net.wandroid.mythology.bait;

import net.wandroid.mythology.Player;
import net.wandroid.mythology.PlayerBaitMap;
import net.wandroid.mythology.PlayerInfoBarFragment;
import net.wandroid.mythology.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class BaitShopActivity extends Activity implements BaitShopListFragment.BaitShopListListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bait_shop_activity);
		// Show the Up button in the action bar.
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.bait_create, menu);
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
		int gold=Player.getInstance().getGold();
		if(gold-bait.getCost()<0){
			//cannot afford, show message
			AlertDialog.Builder builder =new AlertDialog.Builder(this);
			builder.setMessage("You cannot afford this bait").setNeutralButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).show();
		}else{
			final Bait finalBait=bait;
			DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(which==DialogInterface.BUTTON_POSITIVE){
						Player.getInstance().changeGold(-finalBait.getCost());
						PlayerBaitMap.getInstance().add(finalBait);
						((PlayerInfoBarFragment)getFragmentManager().findFragmentById(R.id.bait_shop_player_info_bar_frag)).setPlayerGoldText(Player.getInstance().getGold());
					}else{
						//nop
					}
					dialog.dismiss();
				}
			};
			
			AlertDialog.Builder builder =new AlertDialog.Builder(this);
			String goldStr=getResources().getString(R.string.gold_str);
			builder.setMessage("Do you want to buy "+ bait.getName()+ " for "+ bait.getCost()+" "+goldStr+" ?").setPositiveButton("Yes", listener).setNegativeButton("No", listener).show();
		}
	
	}

}
