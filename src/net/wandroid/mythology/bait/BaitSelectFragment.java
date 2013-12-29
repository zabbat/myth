package net.wandroid.mythology.bait;

import net.wandroid.mythology.PlayerBaitMap;
import net.wandroid.mythology.R;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaitSelectFragment extends Fragment {

	private BaitSelectedListener mBaitSelectedListener=BaitSelectedListener.NullBaitSelectedListener;
	private TextView mNameTextView;
	private Bait mBait=Bait.NullBait;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.bait_select_view, container, false);
		view.findViewById(R.id.bait_select_add_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			Intent intent=new Intent();
			intent.setClass(getActivity(), BaitActivity.class);
			intent.putExtra(BaitActivity.BAIT_ACTIVITY_START_AS_PICKER, true);
			startActivityForResult(intent, 0);
			}
		});
		init(view);
		return view;
	}
	
	private void init(View view){
		mNameTextView=(TextView) view.findViewById(R.id.bait_select_name_text);
		mNameTextView.setText("Bait: "+mBait.getName());
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof BaitSelectedListener){
			mBaitSelectedListener=(BaitSelectedListener) activity;
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mBaitSelectedListener=BaitSelectedListener.NullBaitSelectedListener;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode!=Activity.RESULT_OK){
			//error
			return;
		}
		
		//Not a good solution, should not recreate the Bait by the factory method
		String baitName=data.getStringExtra(Bait.BAIT_NAME_KEY);
		mBait=Bait.baitFactory(baitName);
		PlayerBaitMap.getInstance().decrease(mBait);
		init(getView());
		mBaitSelectedListener.onBaitSelected(mBait, this);
	}
	
	public void setBait(Bait bait){
		mBait=bait;
		init(getView());
	}
	
	public interface BaitSelectedListener{
		void onBaitSelected(Bait bait,  Fragment sender);
		//Null object
		public static BaitSelectedListener NullBaitSelectedListener=new BaitSelectedListener() {
			@Override
			public void onBaitSelected(Bait bait, Fragment sender) {
			}
		};
	}
	
}
