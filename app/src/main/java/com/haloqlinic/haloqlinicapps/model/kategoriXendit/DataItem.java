package com.haloqlinic.haloqlinicapps.model.kategoriXendit;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("icon")
	private Object icon;

	@SerializedName("kategori")
	private String kategori;

	@SerializedName("id")
	private String id;

	public void setIcon(Object icon){
		this.icon = icon;
	}

	public Object getIcon(){
		return icon;
	}

	public void setKategori(String kategori){
		this.kategori = kategori;
	}

	public String getKategori(){
		return kategori;
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
			"icon = '" + icon + '\'' + 
			",kategori = '" + kategori + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}