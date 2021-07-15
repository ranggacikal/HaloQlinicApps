package com.haloqlinic.haloqlinicapps.model.artikel;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private String img;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_artikel")
	private String idArtikel;

	@SerializedName("judul")
	private String judul;

	@SerializedName("created_by")
	private String createdBy;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIdArtikel(String idArtikel){
		this.idArtikel = idArtikel;
	}

	public String getIdArtikel(){
		return idArtikel;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"img = '" + img + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_artikel = '" + idArtikel + '\'' + 
			",judul = '" + judul + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			"}";
		}
}