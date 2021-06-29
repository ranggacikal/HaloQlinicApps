package com.haloqlinic.haloqlinicapps.model.listDokterTersedia;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private String img;

	@SerializedName("nama")
	private String nama;

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("pengalaman")
	private Object pengalaman;

	@SerializedName("spesialis")
	private String spesialis;

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

	public void setBiaya(String biaya){
		this.biaya = biaya;
	}

	public String getBiaya(){
		return biaya;
	}

	public void setPengalaman(Object pengalaman){
		this.pengalaman = pengalaman;
	}

	public Object getPengalaman(){
		return pengalaman;
	}

	public void setSpesialis(String spesialis){
		this.spesialis = spesialis;
	}

	public String getSpesialis(){
		return spesialis;
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
			",biaya = '" + biaya + '\'' + 
			",pengalaman = '" + pengalaman + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}