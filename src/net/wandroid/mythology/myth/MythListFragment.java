package net.wandroid.mythology.myth;

import net.wandroid.mythology.PlayerMythList;
import net.wandroid.mythology.R;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MythListFragment extends ListFragment {
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ArrayAdapter<Myth> adapter =new MythListAdapter(getActivity(), R.id.myth_item_name_text, PlayerMythList.getInstance());
		setListAdapter(adapter);
	}

	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
	}	
	
}
