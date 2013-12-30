package net.wandroid.mythology.bait;

import java.util.ArrayList;
import java.util.List;

import net.wandroid.mythology.PlayerBaitMap;
import net.wandroid.mythology.R;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BaitPlayerListFragment extends ListFragment{

	private BaitListListener mBaitListListener=BaitListListener.NullBaitListListener;
	
	//in case the list is used for picking, None , should also be added to the list
	private boolean mIsPicker;
	
	private ArrayAdapter<Bait> mAdapter;
	private List<Bait> mBaitList=new ArrayList<Bait>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		loadBaitList();
		mAdapter=new BaitPlayerListAdapter(getActivity(), R.id.bait_item_image, mBaitList);
		setListAdapter(mAdapter);
	}

	private void loadBaitList(){
		mBaitList.clear();
		mBaitList.addAll(new ArrayList<Bait>(PlayerBaitMap.getInstance().getBaits()));
		if(mIsPicker){
			mBaitList.add(0, Bait.NullBait);
		}
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
	
	
	
	public void setIsPickerList(boolean b) {
		this.mIsPicker = b;
	}

	@Override
	public void onResume() {
		super.onResume();
		loadBaitList();
		mAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		mBaitListListener.onListItemClicked((Bait) getListAdapter().getItem((int) id));
	}
	
	public interface BaitListListener{
		void onListItemClicked(Bait bait);
		//Null object pattern
		public static final BaitListListener NullBaitListListener=new BaitListListener() {
			@Override
			public void onListItemClicked(Bait bait) {
			}
		};
	}
	
}
