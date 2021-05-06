package com.haloqlinic.haloqlinicapps.model.detailDokter;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("jadwal")
	private String jadwal;

	public void setJadwal(String jadwal){
		this.jadwal = jadwal;
	}

	public String getJadwal(){
		return jadwal;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"jadwal = '" + jadwal + '\'' + 
			"}";
		}
}