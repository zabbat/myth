package net.wandroid.mythology.bait;

public class Ingredient {

	private String mName;
	private int mCost;

	
	public String getName() {
		return mName;
	}

	public int getCost() {
		return mCost;
	}

	public static Ingredient generateHumanFlesh(){
		Ingredient i=new Ingredient();
		i.mName="Human Flesh";
		i.mCost=20;
		return i;
	}

	@Override
	public String toString() {
		return mName;
	}

	@Override
	public int hashCode() {
		return mName.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Ingredient){
			Ingredient i = (Ingredient) o;
			return mName.equals(i.mName);
		}
		return false;
	}
	
	public static final Ingredient NullIngredient=new Ingredient(){
		public String getName() {
			return "None";
		}
	}; 
	
	public static Ingredient generateGarlic(){
		Ingredient i=new Ingredient();
		i.mName="Garlic";
		i.mCost=5;
		return i;
	}
}
