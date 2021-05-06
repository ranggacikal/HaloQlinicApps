package com.haloqlinic.haloqlinicapps.model.jadwalDokter;

import com.google.gson.annotations.SerializedName;

public class ListItem{

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
			"ListItem{" + 
			"jadwal = '" + jadwal + '\'' + 
			"}";
		}
}