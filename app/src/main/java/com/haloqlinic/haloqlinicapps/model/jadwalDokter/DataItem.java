package com.haloqlinic.haloqlinicapps.model.jadwalDokter;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private String img;

	@SerializedName("player_id")
	private String playerId;

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("nama")
	private String nama;

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("spesialis")
	private String spesialis;

	@SerializedName("list")
	private List<ListItem> list;

	@SerializedName("id_dokter")
	private String idDokter;

	@SerializedName("token")
	private String token;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setPlayerId(String playerId){
		this.playerId = playerId;
	}

	public String getPlayerId(){
		return playerId;
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

	public void setBiaya(String biaya){
		this.biaya = biaya;
	}

	public String getBiaya(){
		return biaya;
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

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"img = '" + img + '\'' + 
			",player_id = '" + playerId + '\'' + 
			",id_kategori = '" + idKategori + '\'' + 
			",nama = '" + nama + '\'' + 
			",biaya = '" + biaya + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",list = '" + list + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}