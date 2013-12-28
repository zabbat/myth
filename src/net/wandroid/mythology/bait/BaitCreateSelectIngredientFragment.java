package net.wandroid.mythology.bait;

import net.wandroid.mythology.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaitCreateSelectIngredientFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.bait_create_ingredient_view, container, false);
		return view;
	}
	
	
}
