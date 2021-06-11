package com.haloqlinic.haloqlinicapps.model.loginapi;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("usia")
	private String usia;

	@SerializedName("jk")
	private String jk;

	@SerializedName("img")
	private Object img;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("nama_provinsi")
	private String namaProvinsi;

	@SerializedName("id_customer")
	private String idCustomer;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("uid")
	private Object uid;

	@SerializedName("password")
	private String password;

	@SerializedName("player_id")
	private String playerId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("email")
	private String email;

	@SerializedName("kota")
	private String kota;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("token")
	private String token;

	@SerializedName("oauthpro")
	private Object oauthpro;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama_kecamatan")
	private String namaKecamatan;

	@SerializedName("oauthid")
	private Object oauthid;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("nama_kota")
	private String namaKota;

	@SerializedName("status")
	private String status;

	public String getProvinsi(){
		return provinsi;
	}

	public String getUsia(){
		return usia;
	}

	public String getJk(){
		return jk;
	}

	public Object getImg(){
		return img;
	}

	public String getNoHp(){
		return noHp;
	}

	public String getNamaProvinsi(){
		return namaProvinsi;
	}

	public String getIdCustomer(){
		return idCustomer;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Object getUid(){
		return uid;
	}

	public String getPassword(){
		return password;
	}

	public String getPlayerId(){
		return playerId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getKode(){
		return kode;
	}

	public String getEmail(){
		return email;
	}

	public String getKota(){
		return kota;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public String getToken(){
		return token;
	}

	public Object getOauthpro(){
		return oauthpro;
	}

	public String getAlamat(){
		return alamat;
	}

	public String getNamaKecamatan(){
		return namaKecamatan;
	}

	public Object getOauthid(){
		return oauthid;
	}

	public String getNama(){
		return nama;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public String getNamaKota(){
		return namaKota;
	}

	public String getStatus(){
		return status;
	}
}