package com.haloqlinic.haloqlinicapps.model.jadwalDokter;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("nama")
	private String nama;

	@SerializedName("spesialis")
	private String spesialis;

	@SerializedName("list")
	private List<ListItem> list;

	@SerializedName("id_dokter")
	private String idDokter;

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

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
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
			"id_kategori = '" + idKategori + '\'' + 
			",nama = '" + nama + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",list = '" + list + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			"}";
		}
}