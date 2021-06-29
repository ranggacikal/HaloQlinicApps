package com.haloqlinic.haloqlinicapps.model.listKonsultasi;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private String img;

	@SerializedName("mulai_konsultasi")
	private String mulaiKonsultasi;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("id_dokter")
	private String idDokter;

	@SerializedName("token")
	private String token;

	@SerializedName("jadwal")
	private String jadwal;

	@SerializedName("player_id")
	private String playerId;

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("biaya_admin")
	private Object biayaAdmin;

	@SerializedName("nama")
	private String nama;

	@SerializedName("spesialis")
	private String spesialis;

	@SerializedName("jumlah_bayar")
	private Object jumlahBayar;

	@SerializedName("batas_konsultasi")
	private String batasKonsultasi;

	@SerializedName("status_transaksi")
	private String statusTransaksi;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setMulaiKonsultasi(String mulaiKonsultasi){
		this.mulaiKonsultasi = mulaiKonsultasi;
	}

	public String getMulaiKonsultasi(){
		return mulaiKonsultasi;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
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

	public void setJadwal(String jadwal){
		this.jadwal = jadwal;
	}

	public String getJadwal(){
		return jadwal;
	}

	public void setPlayerId(String playerId){
		this.playerId = playerId;
	}

	public String getPlayerId(){
		return playerId;
	}

	public void setBiaya(String biaya){
		this.biaya = biaya;
	}

	public String getBiaya(){
		return biaya;
	}

	public void setBiayaAdmin(Object biayaAdmin){
		this.biayaAdmin = biayaAdmin;
	}

	public Object getBiayaAdmin(){
		return biayaAdmin;
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

	public void setJumlahBayar(Object jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public Object getJumlahBayar(){
		return jumlahBayar;
	}

	public void setBatasKonsultasi(String batasKonsultasi){
		this.batasKonsultasi = batasKonsultasi;
	}

	public String getBatasKonsultasi(){
		return batasKonsultasi;
	}

	public void setStatusTransaksi(String statusTransaksi){
		this.statusTransaksi = statusTransaksi;
	}

	public String getStatusTransaksi(){
		return statusTransaksi;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"img = '" + img + '\'' + 
			",mulai_konsultasi = '" + mulaiKonsultasi + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			",token = '" + token + '\'' + 
			",jadwal = '" + jadwal + '\'' + 
			",player_id = '" + playerId + '\'' + 
			",biaya = '" + biaya + '\'' + 
			",biaya_admin = '" + biayaAdmin + '\'' + 
			",nama = '" + nama + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",batas_konsultasi = '" + batasKonsultasi + '\'' + 
			",status_transaksi = '" + statusTransaksi + '\'' + 
			"}";
		}
}