package net.wandroid.mythology.trap;

import java.util.ArrayList;

import net.wandroid.mythology.PlayerBaitList;
import net.wandroid.mythology.R;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class BaitListFragment extends ListFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<Bait> adapter=new BaitListAdapter(getActivity(), R.id.bait_item_image, PlayerBaitList.getInstance());
		setListAdapter(adapter);
	}
	
}
