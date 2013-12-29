package net.wandroid.mythology.bait;

import net.wandroid.mythology.R;

public class Bait {
	public static final String BAIT_NAME_KEY="Bait_bait_name_key";

	public static final String BAIT_HUMAN_FLESH = "Human Flesh";
	private String mName;
	private int mImageResourceId;
	
	public String getName(){
		return mName;
	}
	
	public int getImageResource() {
		return mImageResourceId;
	}

	// Need to replace for custom equals

	@Override
	public int hashCode() {
		return getName().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Bait){
			Bait b =(Bait) o;
			return b.getName().equals(getName());
		}
		return false;
	}
	
	public static Bait NullBait=new Bait(){
		public String getName() {
			return "None";
		}
	};
	
	public static Bait generateHumanFlesh(){
		Bait b=new Bait();
		b.mName=BAIT_HUMAN_FLESH;
		b.mImageResourceId=R.drawable.bait_icon_x;
		return b;
	}
	
	public static Bait baitFactory(String name){
		if(name.equals(BAIT_HUMAN_FLESH)){
			return generateHumanFlesh();
		}
		
		//error, no match
		return NullBait;
	}
}
