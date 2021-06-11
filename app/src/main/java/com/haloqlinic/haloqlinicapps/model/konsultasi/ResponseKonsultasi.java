package com.haloqlinic.haloqlinicapps.model.konsultasi;

import com.google.gson.annotations.SerializedName;

public class ResponseKonsultasi{

	@SerializedName("response")
	private String response;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKonsultasi{" + 
			"response = '" + response + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			"}";
		}
}