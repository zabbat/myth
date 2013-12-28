package net.wandroid.mythology.myth;

import java.util.List;

import net.wandroid.mythology.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MythListAdapter extends ArrayAdapter<Myth>{

	private Context mContext;
	
	public MythListAdapter(Context context, int resource,List<Myth> objects) {
		super(context, resource, objects);
		mContext=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View mythView=null;
		if(convertView==null){
			LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mythView=inflater.inflate(R.layout.myth_item_view, parent,false);			
		}else{
			mythView=convertView;
		}
		Myth myth=getItem(position);
		
		TextView mainText=(TextView) mythView.findViewById(R.id.myth_item_name_text);
		mainText.setText(myth.getName());
		ImageView image=(ImageView) mythView.findViewById(R.id.myth_item_image);
		image.setImageResource(myth.getIconResource());
		return mythView;
	}
	
}
