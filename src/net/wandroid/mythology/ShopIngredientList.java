package net.wandroid.mythology;

import java.util.ArrayList;

import net.wandroid.mythology.bait.Ingredient;

public class ShopIngredientList extends ArrayList<Ingredient>{

	private static ArrayList<Ingredient> sShopIngredientsList;
	
	//sigleton
	private ShopIngredientList() {
	}
	
	public static ArrayList<Ingredient> getInstance(){
		if(sShopIngredientsList==null){
			sShopIngredientsList=new ShopIngredientList();
			sShopIngredientsList.add(Ingredient.generateGarlic());
			sShopIngredientsList.add(Ingredient.generateHumanFlesh());
		}
		return sShopIngredientsList;
	}
	
}
