package net.wandroid.mythology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.wandroid.mythology.myth.Myth;
import net.wandroid.mythology.trap.Bait;

public class PlayerBaitList extends ArrayList<Bait>{

	private static final long serialVersionUID = 8615706992859422082L;
	private static PlayerBaitList sBaitList; 
	private Map<String, Integer> mPlayerBaitStash=new HashMap<String, Integer>();
	
	
	//singleton
	private PlayerBaitList() {
	}
	
	@Override
	public boolean add(Bait object) {
		boolean b=super.add(object);
		if(!b){
			return false;
		}
		Integer nr=mPlayerBaitStash.get(object.getName());
		if(nr==null){
			mPlayerBaitStash.put(object.getName(), 1);
		}else{
			mPlayerBaitStash.put(object.getName(), nr+1);
		}
		return true;
	}
	

	public void delete(Bait object) {
		Integer nr=mPlayerBaitStash.get(object.getName());
		if(nr!=null){
			if(nr==1){
				remove(object);
			}else{
				mPlayerBaitStash.put(object.getName(), nr-1);
			}
		}
	}
	
	public int getNr(int id){
		Integer nr=mPlayerBaitStash.get(get(id).getName());
		return nr==null?0:nr;
	}
	
	@Override
	public boolean remove(Object object) {
		boolean b=super.remove(object);
		if(!b){
			return false;
		}
		if(object instanceof Bait){
			Bait bait=(Bait) object;
			mPlayerBaitStash.remove(bait.getName());
		}
		return true;
	}
	
	@Override
	public void clear() {
		super.clear();
		mPlayerBaitStash.clear();
	}
	
	public static PlayerBaitList getInstance(){
		if(sBaitList==null){
			sBaitList=new PlayerBaitList();
			sBaitList.add(Bait.generateHumanFlesh());
		}
		return sBaitList;
	}
	
}
