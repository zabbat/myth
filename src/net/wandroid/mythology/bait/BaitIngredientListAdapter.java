package net.wandroid.mythology.bait;

import java.util.List;

import net.wandroid.mythology.PlayerBaitList;
import net.wandroid.mythology.PlayerIngredientMap;
import net.wandroid.mythology.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BaitIngredientListAdapter extends ArrayAdapter<Ingredient>{

	private Context mContext;
	
	public BaitIngredientListAdapter(Context context, int resource,List<Ingredient> objects) {
		super(context, resource, objects);
		mContext=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View ingredientView=null;
		if(convertView==null){
			LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ingredientView=inflater.inflate(R.layout.ingredient_item_view, parent,false);			
		}else{
			ingredientView=convertView;
		}
		Ingredient ingredient=getItem(position);
		
		TextView nameText=(TextView) ingredientView.findViewById(R.id.ingredient_item_name_text);
		nameText.setText("Ingredient: "+ingredient.getName());
		
		TextView nrText=(TextView) ingredientView.findViewById(R.id.ingredient_item_cost_text);
		int nr=PlayerIngredientMap.getInstance().getNr(ingredient);
		if(nr<1){
			nrText.setText("Cost : "+ingredient.getCost());
		}else{
			nrText.setText("Nr : "+nr);	
		}
		
		return ingredientView;
	}
	
}
