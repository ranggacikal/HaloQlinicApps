package com.haloqlinic.haloqlinicapps.model.jadwalDokter;

import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("jadwal")
	private String jadwal;

	@SerializedName("tanggal")
	private String tanggal;

	public void setJadwal(String jadwal){
		this.jadwal = jadwal;
	}

	public String getJadwal(){
		return jadwal;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"jadwal = '" + jadwal + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			"}";
		}
}