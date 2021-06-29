package com.haloqlinic.haloqlinicapps.model.detailDokter;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("alumni")
	private Object alumni;

	@SerializedName("str")
	private Object str;

	@SerializedName("img")
	private String img;

	@SerializedName("tentang")
	private String tentang;

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("nama")
	private String nama;

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("data")
	private List<Object> data;

	@SerializedName("tempat_praktik")
	private Object tempatPraktik;

	@SerializedName("pengalaman")
	private Object pengalaman;

	@SerializedName("spesialis")
	private String spesialis;

	@SerializedName("id_dokter")
	private String idDokter;

	public void setAlumni(Object alumni){
		this.alumni = alumni;
	}

	public Object getAlumni(){
		return alumni;
	}

	public void setStr(Object str){
		this.str = str;
	}

	public Object getStr(){
		return str;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setTentang(String tentang){
		this.tentang = tentang;
	}

	public String getTentang(){
		return tentang;
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

	public void setData(List<Object> data){
		this.data = data;
	}

	public List<Object> getData(){
		return data;
	}

	public void setTempatPraktik(Object tempatPraktik){
		this.tempatPraktik = tempatPraktik;
	}

	public Object getTempatPraktik(){
		return tempatPraktik;
	}

	public void setPengalaman(Object pengalaman){
		this.pengalaman = pengalaman;
	}

	public Object getPengalaman(){
		return pengalaman;
	}

	public void setSpesialis(String spesialis){
		this.spesialis = spesialis;
	}

	public String getSpesialis(){
		return spesialis;
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
			"ResultItem{" + 
			"alumni = '" + alumni + '\'' + 
			",str = '" + str + '\'' + 
			",img = '" + img + '\'' + 
			",tentang = '" + tentang + '\'' + 
			",id_kategori = '" + idKategori + '\'' + 
			",nama = '" + nama + '\'' + 
			",biaya = '" + biaya + '\'' + 
			",data = '" + data + '\'' + 
			",tempat_praktik = '" + tempatPraktik + '\'' + 
			",pengalaman = '" + pengalaman + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			"}";
		}
}