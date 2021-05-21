package com.haloqlinic.haloqlinicapps.model.listKonsultasi;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("jadwal")
	private String jadwal;

	@SerializedName("img")
	private String img;

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("biaya_admin")
	private Object biayaAdmin;

	@SerializedName("nama")
	private String nama;

	@SerializedName("spesialis")
	private String spesialis;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("jumlah_bayar")
	private Object jumlahBayar;

	@SerializedName("id_dokter")
	private String idDokter;

	public void setJadwal(String jadwal){
		this.jadwal = jadwal;
	}

	public String getJadwal(){
		return jadwal;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
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

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setJumlahBayar(Object jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public Object getJumlahBayar(){
		return jumlahBayar;
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
			"jadwal = '" + jadwal + '\'' + 
			",img = '" + img + '\'' + 
			",biaya = '" + biaya + '\'' + 
			",biaya_admin = '" + biayaAdmin + '\'' + 
			",nama = '" + nama + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			"}";
		}
}