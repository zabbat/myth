package net.wandroid.mythology.trap;

import java.util.List;

import net.wandroid.mythology.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TrapListAdapter extends ArrayAdapter<Trap>{

	private Context mContext;
	
	public TrapListAdapter(Context context, int resource,List<Trap> objects) {
		super(context, resource, objects);
		mContext=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View trapView=null;
		if(convertView==null){
			LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			trapView=inflater.inflate(R.layout.trap_item_view, parent,false);			
		}else{
			trapView=convertView;
		}
		Trap trap=getItem(position);
		
		TextView mainText=(TextView) trapView.findViewById(R.id.trap_item_main_text);
		mainText.setText(trap.getTitle());
		ImageView image=(ImageView) trapView.findViewById(R.id.trap_item_image);
		image.setImageResource(trap.getImageResource());
		return trapView;
	}
	
}
