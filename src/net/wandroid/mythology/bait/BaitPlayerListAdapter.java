package net.wandroid.mythology.bait;

import java.util.List;

import net.wandroid.mythology.PlayerBaitMap;
import net.wandroid.mythology.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BaitPlayerListAdapter extends ArrayAdapter<Bait>{

	private Context mContext;
	
	public BaitPlayerListAdapter(Context context, int resource,List<Bait> objects) {
		super(context, resource, objects);
		mContext=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View baitView=null;
		if(convertView==null){
			LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			baitView=inflater.inflate(R.layout.bait_item_view, parent,false);			
		}else{
			baitView=convertView;
		}
		Bait bait=getItem(position);
		
		TextView mainText=(TextView) baitView.findViewById(R.id.bait_item_name_text);
		mainText.setText(bait.getName());
		
		TextView nrText=(TextView) baitView.findViewById(R.id.bait_item_nr_text);
		//"None" doesn't have quantity
		if(bait==Bait.NullBait){
			nrText.setText("");			
		}else{
			String nrString=mContext.getResources().getString(R.string.bait_item_view_nr_str);
			nrText.setText(nrString+PlayerBaitMap.getInstance().getNr(bait));
		}
		
		TextView costText=(TextView) baitView.findViewById(R.id.bait_item_cost_text);
		costText.setVisibility(View.GONE);
		
		ImageView image=(ImageView) baitView.findViewById(R.id.bait_item_image);
		image.setImageResource(bait.getImageResource());
		return baitView;
	}
	
}
