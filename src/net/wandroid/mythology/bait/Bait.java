package net.wandroid.mythology.bait;

import net.wandroid.mythology.R;

public class Bait {
	public static final String BAIT_NAME_KEY="Bait_bait_name_key";

	public static final String BAIT_HUMAN_FLESH = "Human Flesh";
	private static final String BAIT_GARLIC = "Garlic";
	private static final String BAIT_BOTTLE_OF_WATER = "Bottle of Water";
	
	
	private String mName;
	private int mImageResourceId;
	private int mCost;
	
	public String getName(){
		return mName;
	}
	
	public int getImageResource() {
		return mImageResourceId;
	}

	public int getCost() {
		return mCost;
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
		b.mCost=50;
		return b;
	}
	
	public static Bait generateGarlic(){
		Bait b=new Bait();
		b.mName=BAIT_GARLIC;
		b.mImageResourceId=R.drawable.tips_x;
		b.mCost=1;
		return b;
	}
	
	public static Bait generateBottleOfWater(){
		Bait b=new Bait();
		b.mName=BAIT_BOTTLE_OF_WATER;
		b.mImageResourceId=R.drawable.tips_x;
		b.mCost=5;
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
