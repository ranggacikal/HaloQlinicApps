package com.haloqlinic.haloqlinicapps.model.detailProduk;

import com.google.gson.annotations.SerializedName;

public class VariasiItem{

	@SerializedName("variasi")
	private Object variasi;

	@SerializedName("stok")
	private String stok;

	public void setVariasi(Object variasi){
		this.variasi = variasi;
	}

	public Object getVariasi(){
		return variasi;
	}

	public void setStok(String stok){
		this.stok = stok;
	}

	public String getStok(){
		return stok;
	}

	@Override
 	public String toString(){
		return 
			"VariasiItem{" + 
			"variasi = '" + variasi + '\'' + 
			",stok = '" + stok + '\'' + 
			"}";
		}
}