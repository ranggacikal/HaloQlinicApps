package com.haloqlinic.haloqlinicapps.model.xenditQris;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("branch_code")
	private String branchCode;

	public void setBranchCode(String branchCode){
		this.branchCode = branchCode;
	}

	public String getBranchCode(){
		return branchCode;
	}

	@Override
 	public String toString(){
		return 
			"Metadata{" + 
			"branch_code = '" + branchCode + '\'' + 
			"}";
		}
}