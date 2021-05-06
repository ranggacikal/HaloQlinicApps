package com.haloqlinic.haloqlinicapps.model.updateKurir;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateKurir{

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
			"ResponseUpdateKurir{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}