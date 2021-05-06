package com.haloqlinic.haloqlinicapps.model.dataEkspedisi;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("ekspedisi")
	private String ekspedisi;

	@SerializedName("id")
	private String id;

	@SerializedName("value")
	private String value;

	public void setEkspedisi(String ekspedisi){
		this.ekspedisi = ekspedisi;
	}

	public String getEkspedisi(){
		return ekspedisi;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"ekspedisi = '" + ekspedisi + '\'' + 
			",id = '" + id + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}