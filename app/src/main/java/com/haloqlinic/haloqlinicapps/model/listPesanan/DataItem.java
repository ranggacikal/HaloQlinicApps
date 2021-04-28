package com.haloqlinic.haloqlinicapps.model.listPesanan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("kota")
	private String kota;

	@SerializedName("produk")
	private List<ProdukItem> produk;

	@SerializedName("total_belanja")
	private String totalBelanja;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("total_berat")
	private String totalBerat;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setProduk(List<ProdukItem> produk){
		this.produk = produk;
	}

	public List<ProdukItem> getProduk(){
		return produk;
	}

	public void setTotalBelanja(String totalBelanja){
		this.totalBelanja = totalBelanja;
	}

	public String getTotalBelanja(){
		return totalBelanja;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setKecamatan(String kecamatan){
		this.kecamatan = kecamatan;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public void setTotalBerat(String totalBerat){
		this.totalBerat = totalBerat;
	}

	public String getTotalBerat(){
		return totalBerat;
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
			"provinsi = '" + provinsi + '\'' + 
			",kota = '" + kota + '\'' + 
			",produk = '" + produk + '\'' + 
			",total_belanja = '" + totalBelanja + '\'' + 
			",id_member = '" + idMember + '\'' + 
			",kecamatan = '" + kecamatan + '\'' + 
			",total_berat = '" + totalBerat + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			"}";
		}
}