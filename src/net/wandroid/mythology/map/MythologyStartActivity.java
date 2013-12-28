package net.wandroid.mythology.map;

import java.nio.channels.FileChannel.MapMode;

import net.wandroid.mythology.DeveloperActivity;
import net.wandroid.mythology.PlayerTrapList;
import net.wandroid.mythology.R;
import net.wandroid.mythology.map.MapMenuFragment.MapMenuItem;
import net.wandroid.mythology.myth.HomeActivity;
import net.wandroid.mythology.trap.Trap;
import net.wandroid.mythology.trap.TrapActivity;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.MarkerOptionsCreator;

public class MythologyStartActivity extends Activity implements
		MapZoomListener, MapMenuFragment.MapMenuListener {

	private static final float CLOSEUP_ZOOM_THRESHOLD = 5.9f;
	private static final LatLng HOME_POSITION = new LatLng(55.44, 13.12);
	private static final String HOME_TITLE = "Home";
	private static final String HOME_SNIPPET = "Your home";
	public static final boolean DEBUG = true;

	public enum MAP_MODE {
		MAP_NORMAL, MAP_PLACE_TRAP;
		public static final String MAP_MODE_TYPE_KEY = "MAP_MODE_TYPE";
	};

	private Fragment mMapInfoBar;

	private MAP_MODE mMapMode = MAP_MODE.MAP_NORMAL;
	private GoogleMap mMap;
	private Marker mHomeMarker;
	private BitmapDescriptor mHomeCloseupIcon;
	private BitmapDescriptor mHomePinIcon;

	private Trap mTrapToPlace = Trap.NullTrap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_activity_mythology_start);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.myth_map_frag)).getMap();
		mHomeCloseupIcon = BitmapDescriptorFactory
				.fromResource(R.drawable.home_x);
		mHomePinIcon = BitmapDescriptorFactory
				.fromResource(R.drawable.home_marker_pin_map_gps_x);
		mHomeMarker = mMap.addMarker(new MarkerOptions()
				.position(HOME_POSITION).title(HOME_TITLE).icon(mHomePinIcon)
				.snippet(HOME_SNIPPET));
		mMap.setOnCameraChangeListener(new MythMapOnCameraChangeListener(mMap
				.getCameraPosition().zoom, this));
		mMap.setOnMapLongClickListener(new MythMapLongPressListener());
		mMapInfoBar = getFragmentManager().findFragmentById(
				R.id.map_info_bar_frag);
		mMapInfoBar.getView().setVisibility(View.INVISIBLE);
		
		addTrapsToMap();
		
		Intent intent=getIntent();
		if(intent.hasExtra(MAP_MODE.MAP_MODE_TYPE_KEY)){
			setMapMode(intent);
		}
		
		
	}

	private void addTrapsToMap(){
		for(Trap t:PlayerTrapList.getInstance()){
			if(t.isPlaced()){
				mMap.addMarker(t.getMarkerOptions());
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mythology_start, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_developer_settings:
			Intent intent = new Intent();
			intent.setClass(this, DeveloperActivity.class);
			startActivity(intent);
			break;
		}

		return true;
	}

	@Override
	public void onZoomChanged(float oldLevel, float newLevel) {
		// zooming in
		if (newLevel > oldLevel && newLevel > CLOSEUP_ZOOM_THRESHOLD) {
			mHomeMarker.setIcon(mHomeCloseupIcon);
		}
		// zooming out
		if (newLevel < oldLevel && newLevel < CLOSEUP_ZOOM_THRESHOLD) {
			mHomeMarker.setIcon(mHomePinIcon);
		}
	}

	@Override
	public void onMapMenuItemPressed(MapMenuItem item) {
		Intent intent = new Intent();
		switch (item) {
		case TRAP_ITEM:
			intent.setClass(this, TrapActivity.class);
			startActivityForResult(intent, 0);
			break;
		case HOME_ITEM:
			intent.setClass(this, HomeActivity.class);
			startActivityForResult(intent, 0);
			break;
		default:
			// error
			if (DEBUG) {
				Log.e(getClass().getSimpleName(), "unknown menu item:"+ item.name());
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != Activity.RESULT_OK) {
			// handle error
			return;
		}
		mMapMode = (MAP_MODE) data
				.getSerializableExtra(MAP_MODE.MAP_MODE_TYPE_KEY);

		setMapMode(data);

	}

	private void setMapMode(Intent data){
		mMapMode = (MAP_MODE) data
				.getSerializableExtra(MAP_MODE.MAP_MODE_TYPE_KEY);

		switch (mMapMode) {
		case MAP_NORMAL:
			setNormalMode();
			break;
		case MAP_PLACE_TRAP:
			int trapId = data.getIntExtra(PlayerTrapList.TRAP_ID_KEY, -1);
			setPlaceTrapMode(trapId);
			break;
		default:
			// error
			if (DEBUG) {
				Log.e(getClass().getSimpleName(), "unknown map mode:"
						+ mMapMode.name());
			}
		}
	}
	
	private void setNormalMode() {
		mMapMode = MAP_MODE.MAP_NORMAL;
		mTrapToPlace = Trap.NullTrap;
		mMapInfoBar.getView().setVisibility(View.INVISIBLE);
	}

	private void setPlaceTrapMode(int trapId) {
		// change to place trap UI
		mMapInfoBar.getView().setVisibility(View.VISIBLE);
		mTrapToPlace = PlayerTrapList.getInstance().get(trapId);
		Toast.makeText(this, "placing:" + mTrapToPlace.getTitle(),
				Toast.LENGTH_SHORT).show();
	}

	private class MythMapOnCameraChangeListener implements
			OnCameraChangeListener {
		private float mZoomLevel;
		private MapZoomListener mZoomListener;

		public MythMapOnCameraChangeListener(float zoomLevel,
				MapZoomListener mapZoomListener) {
			mZoomLevel = zoomLevel;
			mZoomListener = mapZoomListener;
		}

		@Override
		public void onCameraChange(CameraPosition position) {
			if (position.zoom != mZoomLevel) {
				mZoomListener.onZoomChanged(mZoomLevel, position.zoom);
			}
			mZoomLevel = position.zoom;
		}

	}

	private class MythMapLongPressListener implements
			GoogleMap.OnMapLongClickListener {
		@Override
		public void onMapLongClick(LatLng point) {
			if (mMapMode == MAP_MODE.MAP_PLACE_TRAP) {
				mTrapToPlace.setPosition(point);				
				Marker marker=mMap.addMarker(mTrapToPlace.getMarkerOptions());
				mTrapToPlace.setMarker(marker);
				mTrapToPlace.setIsPlaced(true);
				setNormalMode();
			}
		}
	}

}
