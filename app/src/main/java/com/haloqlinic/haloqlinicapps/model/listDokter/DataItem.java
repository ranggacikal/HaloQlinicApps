package com.haloqlinic.haloqlinicapps.model.listDokter;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private String img;

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("nama")
	private String nama;

	@SerializedName("spesialis")
	private String spesialis;

	@SerializedName("id_dokter")
	private String idDokter;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setIdKategori(String idKategori){
		this.idKategori = idKategori;
	}

	public String getIdKategori(){
		return idKategori;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
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

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"img = '" + img + '\'' + 
			",id_kategori = '" + idKategori + '\'' + 
			",nama = '" + nama + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			"}";
		}
}