package net.wandroid.mythology.trap;

import net.wandroid.mythology.R;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Trap {

	public enum TrapType {
		IRON_TRAP {
			@Override
			String getTypeString() {
				return "iron";
			}
		};
		
		abstract String getTypeString();
	}

	public static final int SMALL_SIZE = 10;

	public static final int MEDIUM_SIZE = 20;

	public static final int LARGE_SIZE = 30;

	private String mName;
	private TrapType mType;
	private int mSize;
	private int mImageResource;
	private int mMarkerImageResource;
	private Marker mMarker=null;
	private MarkerOptions mMarkerOptions=new MarkerOptions();
	private boolean mIsPlaced;
	private Bait mBait=Bait.NullBait;

	public String getTitle() {
		return (getSizeString()+" "+mType.getTypeString()+" "+mName).trim();
	}

	public String getSizeString() {
		switch (mSize) {
		case SMALL_SIZE:
			return "small";
		case MEDIUM_SIZE:
			return "medium";
		case LARGE_SIZE:
			return "large";
		default:
			return "";
		}
	}
	
	public int getImageResource(){
		return mImageResource;
	}
	
	public int getMapImageResource(){
		return mMarkerImageResource;
	}
	
	
	
	public void setMarker(Marker marker){
		mMarker=marker;
	}

	public MarkerOptions getMarkerOptions() {
		return mMarkerOptions;
	}

	public void setMarkerOptions(MarkerOptions markerOptions) {
		mMarkerOptions = markerOptions;
	}

	public void setPosition(LatLng position){
		mMarkerOptions.position(position);
	}
	
	public boolean isPlaced() {
		return mIsPlaced;
	}

	public void setIsPlaced(boolean isPlaced) {
		mIsPlaced = isPlaced;
	}

	public Bait getBait(){
		return mBait;
	}
	
	public boolean hasBait(){
		return mBait!=null && mBait!=Bait.NullBait;
	}


	public void remove(){
		if(mMarker!=null){
			mMarker.remove();
		}
	}
	
	//factory
	public static Trap trapFactory(int size,TrapType type,int id){
		Trap t=new Trap();
		t.mName="Trap";
		t.mSize=size;
		t.mType=type;
		t.mImageResource=id;
		//t.mMarkerImageResource=R.drawable.red_flag_pin_x;
		t.mMarkerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_flag_pin_x));
		t.mIsPlaced=false;
		t.mBait=Bait.NullBait;
		return t;
	}
	
	//Null object pattern
	public static Trap NullTrap=new Trap(){
		public String getTitle() {
			return "Null trap";
		}
		public void remove() {
		}
	};
}
