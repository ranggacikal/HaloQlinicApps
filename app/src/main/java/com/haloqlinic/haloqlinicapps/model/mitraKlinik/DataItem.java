package com.haloqlinic.haloqlinicapps.model.mitraKlinik;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private Object img;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("kode")
	private String kode;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setImg(Object img){
		this.img = img;
	}

	public Object getImg(){
		return img;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"img = '" + img + '\'' + 
			",id_member = '" + idMember + '\'' + 
			",kode = '" + kode + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			"}";
		}
}