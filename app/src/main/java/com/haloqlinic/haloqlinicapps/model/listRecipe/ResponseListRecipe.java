package com.haloqlinic.haloqlinicapps.model.listRecipe;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListRecipe{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ResponseListRecipe{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}