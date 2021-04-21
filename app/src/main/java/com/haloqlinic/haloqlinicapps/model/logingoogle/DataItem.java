package com.haloqlinic.haloqlinicapps.model.logingoogle;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("provinsi")
	private Object provinsi;

	@SerializedName("usia")
	private Object usia;

	@SerializedName("jk")
	private Object jk;

	@SerializedName("img")
	private Object img;

	@SerializedName("kota")
	private Object kota;

	@SerializedName("no_hp")
	private Object noHp;

	@SerializedName("id_customer")
	private String idCustomer;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("tgl_lahir")
	private Object tglLahir;

	@SerializedName("oauthpro")
	private String oauthpro;

	@SerializedName("alamat")
	private Object alamat;

	@SerializedName("oauthid")
	private String oauthid;

	@SerializedName("password")
	private Object password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("kecamatan")
	private Object kecamatan;

	@SerializedName("email")
	private String email;

	public void setProvinsi(Object provinsi){
		this.provinsi = provinsi;
	}

	public Object getProvinsi(){
		return provinsi;
	}

	public void setUsia(Object usia){
		this.usia = usia;
	}

	public Object getUsia(){
		return usia;
	}

	public void setJk(Object jk){
		this.jk = jk;
	}

	public Object getJk(){
		return jk;
	}

	public void setImg(Object img){
		this.img = img;
	}

	public Object getImg(){
		return img;
	}

	public void setKota(Object kota){
		this.kota = kota;
	}

	public Object getKota(){
		return kota;
	}

	public void setNoHp(Object noHp){
		this.noHp = noHp;
	}

	public Object getNoHp(){
		return noHp;
	}

	public void setIdCustomer(String idCustomer){
		this.idCustomer = idCustomer;
	}

	public String getIdCustomer(){
		return idCustomer;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTglLahir(Object tglLahir){
		this.tglLahir = tglLahir;
	}

	public Object getTglLahir(){
		return tglLahir;
	}

	public void setOauthpro(String oauthpro){
		this.oauthpro = oauthpro;
	}

	public String getOauthpro(){
		return oauthpro;
	}

	public void setAlamat(Object alamat){
		this.alamat = alamat;
	}

	public Object getAlamat(){
		return alamat;
	}

	public void setOauthid(String oauthid){
		this.oauthid = oauthid;
	}

	public String getOauthid(){
		return oauthid;
	}

	public void setPassword(Object password){
		this.password = password;
	}

	public Object getPassword(){
		return password;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setKecamatan(Object kecamatan){
		this.kecamatan = kecamatan;
	}

	public Object getKecamatan(){
		return kecamatan;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"provinsi = '" + provinsi + '\'' + 
			",usia = '" + usia + '\'' + 
			",jk = '" + jk + '\'' + 
			",img = '" + img + '\'' + 
			",kota = '" + kota + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",id_customer = '" + idCustomer + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",tgl_lahir = '" + tglLahir + '\'' + 
			",oauthpro = '" + oauthpro + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",oauthid = '" + oauthid + '\'' + 
			",password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",kode = '" + kode + '\'' + 
			",kecamatan = '" + kecamatan + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}