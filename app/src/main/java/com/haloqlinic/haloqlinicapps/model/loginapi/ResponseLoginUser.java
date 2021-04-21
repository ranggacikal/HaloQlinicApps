package com.haloqlinic.haloqlinicapps.model.loginapi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginUser{

	@SerializedName("response")
	private List<ResponseItem> response;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLoginUser{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}