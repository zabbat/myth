package net.wandroid.mythology.trap;

import net.wandroid.mythology.PlayerTrapList;
import net.wandroid.mythology.R;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TrapListFragment extends ListFragment{

	private TrapListListener mTrapListListener=TrapListListener.NullTrapListListener;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View header=getActivity().getLayoutInflater().inflate(R.layout.trap_list_header, null);
		getListView().addHeaderView(header);
		
		ArrayAdapter<Trap> adapter = new TrapListAdapter(getActivity(),R.layout.trap_item_view, PlayerTrapList.getInstance());
		setListAdapter(adapter);
	}
	@Override
	
	public void onAttach(android.app.Activity activity) {
		super.onAttach(activity);
		if(activity instanceof TrapListListener){
			mTrapListListener=(TrapListListener) activity;
		}		
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mTrapListListener=TrapListListener.NullTrapListListener;
	}
	
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		//mTrapListListener.onListItemClicked(id);
		Intent intent=new Intent();
		intent.putExtra(PlayerTrapList.TRAP_ID_KEY, (int)id);
		intent.setClass(getActivity(), TrapInfoActivity.class);
		startActivity(intent);
	}
	
	public interface TrapListListener{
		void onListItemClicked(long id);
		//Null object pattern
		static final TrapListListener NullTrapListListener=new TrapListListener() {
			@Override
			public void onListItemClicked(long id) {
			}
		}; 
	}
}
