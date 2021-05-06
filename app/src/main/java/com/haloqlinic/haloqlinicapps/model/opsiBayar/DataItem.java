package com.haloqlinic.haloqlinicapps.model.opsiBayar;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("kode")
	private String kode;

	@SerializedName("icon")
	private String icon;

	@SerializedName("id")
	private String id;

	@SerializedName("opsi_bayar")
	private String opsiBayar;

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setOpsiBayar(String opsiBayar){
		this.opsiBayar = opsiBayar;
	}

	public String getOpsiBayar(){
		return opsiBayar;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"kode = '" + kode + '\'' + 
			",icon = '" + icon + '\'' + 
			",id = '" + id + '\'' + 
			",opsi_bayar = '" + opsiBayar + '\'' + 
			"}";
		}
}