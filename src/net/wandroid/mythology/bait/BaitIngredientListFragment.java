package net.wandroid.mythology.bait;

import java.util.ArrayList;

import net.wandroid.mythology.PlayerIngredientMap;
import net.wandroid.mythology.R;
import net.wandroid.mythology.ShopIngredientList;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BaitIngredientListFragment extends ListFragment{

	private IngredientListListener mBaitListListener=IngredientListListener.NullIngredientListListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//note that you cannot buy item if you already own it, you must use the owned one.
		//This might change in the future (add a "Buy Ingredient Screen", and then only choose from owned Ingredients)
		ArrayList<Ingredient> arrayList=new ArrayList<Ingredient>(ShopIngredientList.getInstance());
		arrayList.removeAll(PlayerIngredientMap.getInstance().getKeys());
		arrayList.addAll(PlayerIngredientMap.getInstance().getKeys());
		ArrayAdapter<Ingredient> adapter=new BaitIngredientListAdapter(getActivity(), R.id.ingredient_item_name_text, arrayList);
		setListAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof IngredientListListener){
			mBaitListListener=(IngredientListListener) activity;
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mBaitListListener=IngredientListListener.NullIngredientListListener;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		mBaitListListener.onListItemClicked((int) id,(Ingredient) getListAdapter().getItem((int)id));
	}
	
	public interface IngredientListListener{
		void onListItemClicked(int id,Ingredient ingredient);
		//Null object pattern
		public static final IngredientListListener NullIngredientListListener=new IngredientListListener() {
			@Override
			public void onListItemClicked(int id, Ingredient ingredient) {
			}
		};
	}
	
}
