package net.wandroid.mythology.myth;

import net.wandroid.mythology.R;
import net.wandroid.mythology.trap.Trap;

public class Myth {

	private String mName;
	private int mIconResource;
	private int mSize;
	
	public String getName() {
		return mName;
	}

	public int getIconResource() {
		return mIconResource;
	}

	public int getSize() {
		return mSize;
	}
	
	public static Myth generateZombie(){
		Myth m=new Myth();
		m.mName="Zombie";
		m.mIconResource=R.drawable.myth_zombie_x;
		m.mSize=Trap.MEDIUM_SIZE;
		return m;
	}

	public static Myth generateVampire(){
		Myth m=new Myth();
		m.mName="Vampire";
		m.mIconResource=R.drawable.myth_vampire_x;
		m.mSize=Trap.MEDIUM_SIZE;
		return m;
	}
	
}
