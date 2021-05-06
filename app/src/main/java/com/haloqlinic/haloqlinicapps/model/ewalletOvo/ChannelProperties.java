package com.haloqlinic.haloqlinicapps.model.ewalletOvo;

import com.google.gson.annotations.SerializedName;

public class ChannelProperties{

	@SerializedName("mobile_number")
	private String mobileNumber;

	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	public String getMobileNumber(){
		return mobileNumber;
	}

	@Override
 	public String toString(){
		return 
			"ChannelProperties{" + 
			"mobile_number = '" + mobileNumber + '\'' + 
			"}";
		}
}