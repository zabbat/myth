package net.wandroid.mythology.map;

import net.wandroid.mythology.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapMenuFragment extends Fragment{
	private MapMenuListener mMapMenuListener=MapMenuListener.nullMapMenuListener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.map_menu, container);
		view.findViewById(R.id.map_menu_trap).setOnClickListener(new MapIconMenuListener(MapMenuItem.TRAP_ITEM));
		view.findViewById(R.id.map_menu_home).setOnClickListener(new MapIconMenuListener(MapMenuItem.HOME_ITEM));
		view.findViewById(R.id.map_menu_bait).setOnClickListener(new MapIconMenuListener(MapMenuItem.BAIT_ITEM));
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof MapMenuListener){
			mMapMenuListener=(MapMenuListener) activity;
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mMapMenuListener=MapMenuListener.nullMapMenuListener;
	}
	
	
	public enum MapMenuItem{
		TRAP_ITEM,HOME_ITEM,BAIT_ITEM;
	}
	
	public interface MapMenuListener{
		void onMapMenuItemPressed(MapMenuItem item);
		//NUll object pattern
		static final MapMenuListener nullMapMenuListener=new MapMenuListener() {
			@Override
			public void onMapMenuItemPressed(MapMenuItem item) {
			}
		};
	}
	
	//private class to avoid nested local classes when init onclicklisteners
	private class MapIconMenuListener implements View.OnClickListener{
		private final MapMenuItem mItem;
		public MapIconMenuListener(MapMenuItem item) {
			mItem=item;
		}
		@Override
		public void onClick(View v) {
			mMapMenuListener.onMapMenuItemPressed(mItem);
		}
	}
	
}
