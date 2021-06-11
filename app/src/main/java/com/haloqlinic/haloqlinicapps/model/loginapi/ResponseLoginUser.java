package com.haloqlinic.haloqlinicapps.model.loginapi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginUser{

	@SerializedName("response")
	private List<ResponseItem> response;

	public List<ResponseItem> getResponse(){
		return response;
	}
}