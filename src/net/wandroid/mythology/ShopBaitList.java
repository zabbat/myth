package net.wandroid.mythology;

import java.util.ArrayList;
import java.util.List;

import net.wandroid.mythology.bait.Bait;
import net.wandroid.mythology.trap.Trap;
import net.wandroid.mythology.trap.Trap.TrapType;

public class ShopBaitList extends ArrayList<Bait>{

	private static final long serialVersionUID = 588000600272096891L;

	private static List<Bait> sBaitList;
	
	private static final String TAG="ShopbaitList";
	
	public static final String SHOP_BAIT_ID_KEY=TAG+"_bait_id";
	
	//singleton class
	private ShopBaitList() {
	}
	
	public static List<Bait> getInstance(){
		if(sBaitList==null){
			sBaitList=new ShopBaitList();
			sBaitList.add(Bait.generateHumanFlesh());
			sBaitList.add(Bait.generateGarlic());
			sBaitList.add(Bait.generateBottleOfWater());
		}
		return sBaitList;
	}
	
}
