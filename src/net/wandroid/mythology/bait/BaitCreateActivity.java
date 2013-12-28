package net.wandroid.mythology.bait;

import net.wandroid.mythology.PlayerIngredientMap;
import net.wandroid.mythology.R;
import net.wandroid.mythology.R.layout;
import net.wandroid.mythology.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class BaitCreateActivity extends Activity implements BaitIngredientListFragment.IngredientListListener{

	private Ingredient mIngredient1=Ingredient.NullIngredient;
	private Ingredient mIngredient2=Ingredient.NullIngredient;
	private Fragment mIngedientFragment1;
	private Fragment mIngedientFragment2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bait_create_activity);
		// Show the Up button in the action bar.
		setupActionBar();
		mIngedientFragment1=getFragmentManager().findFragmentById(R.id.bait_create_ingredient_1_frag);
		mIngedientFragment2=getFragmentManager().findFragmentById(R.id.bait_create_ingredient_2_frag);
		init();
	}

	private void init(){
		TextView nameView1=(TextView) mIngedientFragment1.getView().findViewById(R.id.bait_create_ingredient_name_text);
		nameView1.setText("Ingredient: "+mIngredient1.getName());
		TextView nameView2=(TextView) mIngedientFragment2.getView().findViewById(R.id.bait_create_ingredient_name_text);
		nameView2.setText("Ingredient: "+mIngredient2.getName());
		if(mIngredient1==Ingredient.NullIngredient){
			mIngedientFragment2.getView().setVisibility(View.INVISIBLE);
		}else{
			mIngedientFragment2.getView().setVisibility(View.VISIBLE);
		}
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
	public void onListItemClicked(int id,final Ingredient ingredient) {
		PlayerIngredientMap playerIngredients=PlayerIngredientMap.getInstance();
		if(mIngredient1==Ingredient.NullIngredient){
			if(playerIngredients.getNr(ingredient)<1){
				//purchase
				playerIngredients.add(ingredient);
				mIngredient1=ingredient;
			}else{
				
			}
		}else if(mIngredient2==Ingredient.NullIngredient){
			if(playerIngredients.getNr(ingredient)<1){
				//purchase
				playerIngredients.add(ingredient);
				mIngredient2=ingredient;
			}else{
				
			}
		}
		init();

	}

}
