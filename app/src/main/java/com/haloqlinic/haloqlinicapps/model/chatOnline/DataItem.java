package com.haloqlinic.haloqlinicapps.model.chatOnline;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("status_konsultasi")
	private String statusKonsultasi;

	@SerializedName("player_id")
	private String playerId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id_dokter")
	private String idDokter;

	@SerializedName("token")
	private String token;

	public void setStatusKonsultasi(String statusKonsultasi){
		this.statusKonsultasi = statusKonsultasi;
	}

	public String getStatusKonsultasi(){
		return statusKonsultasi;
	}

	public void setPlayerId(String playerId){
		this.playerId = playerId;
	}

	public String getPlayerId(){
		return playerId;
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
			"status_konsultasi = '" + statusKonsultasi + '\'' + 
			",player_id = '" + playerId + '\'' + 
			",nama = '" + nama + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}