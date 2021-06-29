package com.haloqlinic.haloqlinicapps.model.jamDokter;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("jam")
	private String jam;

	@SerializedName("id")
	private String id;

	public void setJam(String jam){
		this.jam = jam;
	}

	public String getJam(){
		return jam;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"jam = '" + jam + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}