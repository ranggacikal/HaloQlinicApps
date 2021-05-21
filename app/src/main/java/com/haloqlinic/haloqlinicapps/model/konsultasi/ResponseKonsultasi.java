package com.haloqlinic.haloqlinicapps.model.konsultasi;

import com.google.gson.annotations.SerializedName;

public class ResponseKonsultasi{

	@SerializedName("response")
	private String response;

	public String getResponse(){
		return response;
	}
}