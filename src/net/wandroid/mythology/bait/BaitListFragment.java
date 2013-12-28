package net.wandroid.mythology.bait;

import net.wandroid.mythology.PlayerBaitList;
import net.wandroid.mythology.R;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BaitListFragment extends ListFragment{

	private BaitListListener mBaitListListener=BaitListListener.NullBaitListListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<Bait> adapter=new BaitListAdapter(getActivity(), R.id.bait_item_image, PlayerBaitList.getInstance());
		setListAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof BaitListListener){
			mBaitListListener=(BaitListListener) activity;
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mBaitListListener=BaitListListener.NullBaitListListener;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		mBaitListListener.onListItemClicked((int) id);
	}
	
	public interface BaitListListener{
		void onListItemClicked(int id);
		//Null object pattern
		public static final BaitListListener NullBaitListListener=new BaitListListener() {
			@Override
			public void onListItemClicked(int id) {
			}
		};
	}
	
}
