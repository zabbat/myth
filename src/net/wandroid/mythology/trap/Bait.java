package net.wandroid.mythology.trap;

import net.wandroid.mythology.R;

public class Bait {

	private String mName;
	private int mImageResourceId;
	
	public String getName(){
		return mName;
	}
	
	public int getImageResource() {
		return mImageResourceId;
	}

	public static Bait NullBait=new Bait(){
		public String getName() {
			return "None";
		}
	};
	
	public static Bait generateHumanFlesh(){
		Bait b=new Bait();
		b.mName="Human Flesh";
		b.mImageResourceId=R.drawable.bait_icon_x;
		return b;
	}
}
