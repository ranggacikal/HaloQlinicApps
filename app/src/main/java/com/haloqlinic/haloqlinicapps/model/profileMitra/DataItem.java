package com.haloqlinic.haloqlinicapps.model.profileMitra;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("nm_kecamatan")
	private String nmKecamatan;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("nm_provinsi")
	private String nmProvinsi;

	@SerializedName("nm_kota")
	private String nmKota;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("alamat")
	private String alamat;

	public void setNmKecamatan(String nmKecamatan){
		this.nmKecamatan = nmKecamatan;
	}

	public String getNmKecamatan(){
		return nmKecamatan;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setNmProvinsi(String nmProvinsi){
		this.nmProvinsi = nmProvinsi;
	}

	public String getNmProvinsi(){
		return nmProvinsi;
	}

	public void setNmKota(String nmKota){
		this.nmKota = nmKota;
	}

	public String getNmKota(){
		return nmKota;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"nm_kecamatan = '" + nmKecamatan + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",nm_provinsi = '" + nmProvinsi + '\'' + 
			",nm_kota = '" + nmKota + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}
}