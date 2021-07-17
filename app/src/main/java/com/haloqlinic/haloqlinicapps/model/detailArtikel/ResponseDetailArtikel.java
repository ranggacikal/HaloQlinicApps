package com.haloqlinic.haloqlinicapps.model.detailArtikel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDetailArtikel{

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
			"ResponseDetailArtikel{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}