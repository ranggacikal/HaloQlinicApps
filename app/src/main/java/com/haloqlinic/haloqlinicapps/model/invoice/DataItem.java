package com.haloqlinic.haloqlinicapps.model.invoice;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("ongkir")
	private String ongkir;

	@SerializedName("biaya_admin")
	private String biayaAdmin;

	@SerializedName("total_belanja")
	private String totalBelanja;

	@SerializedName("id_opsi_bayar")
	private String idOpsiBayar;

	@SerializedName("jumlah_bayar")
	private String jumlahBayar;

	@SerializedName("opsi_bayar")
	private String opsiBayar;

	public void setOngkir(String ongkir){
		this.ongkir = ongkir;
	}

	public String getOngkir(){
		return ongkir;
	}

	public void setBiayaAdmin(String biayaAdmin){
		this.biayaAdmin = biayaAdmin;
	}

	public String getBiayaAdmin(){
		return biayaAdmin;
	}

	public void setTotalBelanja(String totalBelanja){
		this.totalBelanja = totalBelanja;
	}

	public String getTotalBelanja(){
		return totalBelanja;
	}

	public void setIdOpsiBayar(String idOpsiBayar){
		this.idOpsiBayar = idOpsiBayar;
	}

	public String getIdOpsiBayar(){
		return idOpsiBayar;
	}

	public void setJumlahBayar(String jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public String getJumlahBayar(){
		return jumlahBayar;
	}

	public void setOpsiBayar(String opsiBayar){
		this.opsiBayar = opsiBayar;
	}

	public String getOpsiBayar(){
		return opsiBayar;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"ongkir = '" + ongkir + '\'' + 
			",biaya_admin = '" + biayaAdmin + '\'' + 
			",total_belanja = '" + totalBelanja + '\'' + 
			",id_opsi_bayar = '" + idOpsiBayar + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",opsi_bayar = '" + opsiBayar + '\'' + 
			"}";
		}
}