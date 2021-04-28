package com.haloqlinic.haloqlinicapps.model.hapusKeranjang;

import com.google.gson.annotations.SerializedName;

public class ResponseHapusKeranjang{

	@SerializedName("response")
	private String response;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseHapusKeranjang{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}