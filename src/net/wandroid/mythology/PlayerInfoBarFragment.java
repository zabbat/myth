package net.wandroid.mythology;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlayerInfoBarFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.player_info_bar_view, container,false);
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setPlayerGoldText(Player.getInstance().getGold());
		setPlayerExpText(0);
	}
	
	public void setPlayerGoldText(int gold){
		String goldStr=getActivity().getResources().getString(R.string.gold_str);
		((TextView)getView().findViewById(R.id.player_info_bar_gold_text)).setText(goldStr+": "+gold);
	}
	
	public void setPlayerExpText(int exp){
		String expStr=getActivity().getResources().getString(R.string.exp_str);
		((TextView)getView().findViewById(R.id.player_info_bar_exp_text)).setText(expStr+": "+exp);
	}

}
