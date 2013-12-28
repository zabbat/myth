package net.wandroid.mythology;

import java.util.ArrayList;
import java.util.List;

import net.wandroid.mythology.trap.Trap;
import net.wandroid.mythology.trap.Trap.TrapType;

public class PlayerTrapList extends ArrayList<Trap>{

	private static final long serialVersionUID = 588000600272096891L;

	private static List<Trap> sTrapList;
	
	private static final String TAG="PlayerTrapList";
	
	public static final String TRAP_ID_KEY=TAG+"_trap_id";
	
	//singleton class
	private PlayerTrapList() {
	}
	
	public static List<Trap> getInstance(){
		if(sTrapList==null){
			sTrapList=new PlayerTrapList();
			sTrapList.add(Trap.trapFactory(Trap.MEDIUM_SIZE, TrapType.IRON_TRAP, R.drawable.cage_x));
			sTrapList.add(Trap.trapFactory(Trap.SMALL_SIZE, TrapType.IRON_TRAP, R.drawable.cage_x));
		}
		return sTrapList;
	}
	
}
