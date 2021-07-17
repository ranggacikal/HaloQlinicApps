package com.haloqlinic.haloqlinicapps.model.ubahPassword;

import com.google.gson.annotations.SerializedName;

public class ResponseUbahPassword{

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
			"ResponseUbahPassword{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}