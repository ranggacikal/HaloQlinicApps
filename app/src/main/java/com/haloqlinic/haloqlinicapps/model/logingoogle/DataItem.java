package com.haloqlinic.haloqlinicapps.model.logingoogle;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("usia")
	private String usia;

	@SerializedName("jk")
	private String jk;

	@SerializedName("img")
	private Object img;

	@SerializedName("kota")
	private String kota;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("id_customer")
	private String idCustomer;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("token")
	private String token;

	@SerializedName("oauthpro")
	private String oauthpro;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("uid")
	private String uid;

	@SerializedName("oauthid")
	private String oauthid;

	@SerializedName("password")
	private Object password;

	@SerializedName("player_id")
	private String playerId;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setUsia(String usia){
		this.usia = usia;
	}

	public String getUsia(){
		return usia;
	}

	public void setJk(String jk){
		this.jk = jk;
	}

	public String getJk(){
		return jk;
	}

	public void setImg(Object img){
		this.img = img;
	}

	public Object getImg(){
		return img;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
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

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setOauthpro(String oauthpro){
		this.oauthpro = oauthpro;
	}

	public String getOauthpro(){
		return oauthpro;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return uid;
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

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setKecamatan(String kecamatan){
		this.kecamatan = kecamatan;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
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
			",token = '" + token + '\'' + 
			",oauthpro = '" + oauthpro + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",uid = '" + uid + '\'' + 
			",oauthid = '" + oauthid + '\'' + 
			",password = '" + password + '\'' + 
			",player_id = '" + playerId + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",kode = '" + kode + '\'' + 
			",kecamatan = '" + kecamatan + '\'' + 
			",email = '" + email + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}