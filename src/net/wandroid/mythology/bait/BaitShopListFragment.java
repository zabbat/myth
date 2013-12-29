package net.wandroid.mythology.bait;

import java.util.ArrayList;

import net.wandroid.mythology.PlayerBaitMap;
import net.wandroid.mythology.R;
import net.wandroid.mythology.ShopBaitList;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BaitShopListFragment extends ListFragment{

	private BaitShopListListener mBaitShopListListener=BaitShopListListener.NullBaitShopListListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayAdapter<Bait> adapter=new BaitShopListAdapter(getActivity(), R.id.bait_item_image, ShopBaitList.getInstance());
		setListAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof BaitShopListListener){
			mBaitShopListListener=(BaitShopListListener) activity;
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mBaitShopListListener=BaitShopListListener.NullBaitShopListListener;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		mBaitShopListListener.onListItemClicked((Bait) getListAdapter().getItem((int) id));
	}
	
	public interface BaitShopListListener{
		void onListItemClicked(Bait bait);
		//Null object pattern
		public static final BaitShopListListener NullBaitShopListListener=new BaitShopListListener() {
			@Override
			public void onListItemClicked(Bait bait) {
			}
		};
	}
	
}
