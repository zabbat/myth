package net.wandroid.mythology;

public class Player {
	
	private static final int PLAYER_START_GOLD = 100;

	//capping gold at minimum 0
	private static final int PLAYER_MIN_GOLD = 0;

	private static Player sPlayer;
	
	private int mGold;

	public int getGold() {
		return mGold;
	}

	public void setGold(int gold) {
		mGold = gold;
	}
	
	public void changeGold(int gold) {
		mGold += gold;
		
		if(mGold<PLAYER_MIN_GOLD){
			mGold=0;
		}
	}
	
	public static Player getInstance(){
		if(sPlayer==null){
			sPlayer=new Player();
			sPlayer.setGold(PLAYER_START_GOLD);
		}
		return sPlayer;
	}
	
	
	
}
