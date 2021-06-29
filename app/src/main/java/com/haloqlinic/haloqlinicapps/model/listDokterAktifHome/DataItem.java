package com.haloqlinic.haloqlinicapps.model.listDokterAktifHome;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private String img;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_dokter")
	private String idDokter;

	@SerializedName("status")
	private String status;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setIdDokter(String idDokter){
		this.idDokter = idDokter;
	}

	public String getIdDokter(){
		return idDokter;
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
			"DataItem{" + 
			"img = '" + img + '\'' + 
			",nama = '" + nama + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}