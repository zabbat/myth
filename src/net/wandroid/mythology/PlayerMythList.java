package net.wandroid.mythology;

import java.util.ArrayList;
import java.util.List;

import net.wandroid.mythology.myth.Myth;

public class PlayerMythList extends ArrayList<Myth>{

	private static final long serialVersionUID = -2913769962123662705L;
	private static List<Myth> sMythList; 
	
	//singleton
	private PlayerMythList() {
	}
	
	public static List<Myth> getInstance(){
		if(sMythList==null){
			sMythList=new PlayerMythList();
			sMythList.add(Myth.generateZombie());
			sMythList.add(Myth.generateVampire());
		}
		return sMythList;
	}
	
}
