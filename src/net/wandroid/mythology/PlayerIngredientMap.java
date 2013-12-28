package net.wandroid.mythology;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.wandroid.mythology.bait.Ingredient;

public class PlayerIngredientMap{
	
	private static PlayerIngredientMap sPlayerIngredientMap;

	private Map<Ingredient, Integer> mMap=new HashMap<Ingredient, Integer>();
	
	//singleton
	private PlayerIngredientMap() {
	}

	public static PlayerIngredientMap getInstance(){
		if(sPlayerIngredientMap==null){
			sPlayerIngredientMap=new PlayerIngredientMap();
		}
		return sPlayerIngredientMap;
	}
	
	public void add(Ingredient i){
		Integer nr=0;
		if(mMap.containsKey(i)){
			nr=mMap.get(i);
		}
		mMap.put(i, nr+1);
	}
	
	public void decrease(Ingredient i){
		Integer nr=0;
		if(mMap.containsKey(i)){
			nr=mMap.get(i) -1;
			if(nr<1){
				mMap.remove(i);
			}else{
				mMap.put(i, nr);
			}
		}
	}
	
	public int getNr(Ingredient i){
		Integer nr=mMap.get(i);
		return nr==null?0:nr;
	}
	
	public Set<Ingredient> getKeys(){
		return mMap.keySet();
	}
	
}
