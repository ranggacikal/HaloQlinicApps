package com.haloqlinic.haloqlinicapps.model.historyTransaksi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseHistoryTransaksi{

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
			"ResponseHistoryTransaksi{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}