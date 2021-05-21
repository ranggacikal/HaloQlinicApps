package com.haloqlinic.haloqlinicapps.model.jadwalDokter;

import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("jadwal")
	private String jadwal;

	@SerializedName("id")
	private String id;

	@SerializedName("status")
	private String status;

	public void setJadwal(String jadwal){
		this.jadwal = jadwal;
	}

	public String getJadwal(){
		return jadwal;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"jadwal = '" + jadwal + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}