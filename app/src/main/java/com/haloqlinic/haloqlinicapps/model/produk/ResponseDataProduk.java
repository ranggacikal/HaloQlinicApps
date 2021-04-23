package com.haloqlinic.haloqlinicapps.model.produk;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataProduk{

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
			"ResponseDataProduk{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}