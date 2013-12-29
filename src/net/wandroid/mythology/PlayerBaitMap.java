package net.wandroid.mythology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.wandroid.mythology.bait.Bait;
import net.wandroid.mythology.myth.Myth;

public class PlayerBaitMap{

	
	private static PlayerBaitMap sBaitMap; 
	private Map<Bait, Integer> mPlayerBaits=new HashMap<Bait, Integer>();
	
	
	//singleton
	private PlayerBaitMap() {
	}
	
	
	public static PlayerBaitMap getInstance(){
		if(sBaitMap==null){
			sBaitMap=new PlayerBaitMap();
			sBaitMap.add(Bait.generateHumanFlesh());
		}
		return sBaitMap;
	}
	
	
	public int getNr(Bait bait){
		Integer nr=mPlayerBaits.get(bait);
		return nr==null?0:nr;
	}
	
	public void add(Bait bait){
		mPlayerBaits.put(bait, getNr(bait)+1);
	}

	public void decrease(Bait bait){
		Integer nr=getNr(bait)-1;
		if(nr<1){
			mPlayerBaits.remove(bait);
		}else{
			mPlayerBaits.put(bait, nr);
		}
	}
	
	public Set<Bait> getBaits(){
		return mPlayerBaits.keySet();
	}
	
}
