package com.haloqlinic.haloqlinicapps.model.profileMitra;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProfileMitra{

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
			"ResponseProfileMitra{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}