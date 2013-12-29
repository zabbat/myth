package net.wandroid.mythology.map;

import net.wandroid.mythology.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapInfobarFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View infoBar=inflater.inflate(R.layout.map_info_bar_view, container);
		return infoBar;
	}
	
}
