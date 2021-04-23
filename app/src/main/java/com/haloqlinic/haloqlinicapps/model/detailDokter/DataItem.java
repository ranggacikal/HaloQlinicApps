package com.haloqlinic.haloqlinicapps.model.detailDokter;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("hari")
	private String hari;

	@SerializedName("mulai")
	private String mulai;

	@SerializedName("akhir")
	private String akhir;

	public void setHari(String hari){
		this.hari = hari;
	}

	public String getHari(){
		return hari;
	}

	public void setMulai(String mulai){
		this.mulai = mulai;
	}

	public String getMulai(){
		return mulai;
	}

	public void setAkhir(String akhir){
		this.akhir = akhir;
	}

	public String getAkhir(){
		return akhir;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"hari = '" + hari + '\'' + 
			",mulai = '" + mulai + '\'' + 
			",akhir = '" + akhir + '\'' + 
			"}";
		}
}