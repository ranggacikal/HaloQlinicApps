package com.haloqlinic.haloqlinicapps.model.xenditQris;

import com.google.gson.annotations.SerializedName;

public class ResponseQris{

	@SerializedName("callback_url")
	private String callbackUrl;

	@SerializedName("amount")
	private int amount;

	@SerializedName("metadata")
	private Metadata metadata;

	@SerializedName("created")
	private String created;

	@SerializedName("external_id")
	private String externalId;

	@SerializedName("id")
	private String id;

	@SerializedName("type")
	private String type;

	@SerializedName("updated")
	private String updated;

	@SerializedName("qr_string")
	private String qrString;

	@SerializedName("status")
	private String status;

	public void setCallbackUrl(String callbackUrl){
		this.callbackUrl = callbackUrl;
	}

	public String getCallbackUrl(){
		return callbackUrl;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setMetadata(Metadata metadata){
		this.metadata = metadata;
	}

	public Metadata getMetadata(){
		return metadata;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setExternalId(String externalId){
		this.externalId = externalId;
	}

	public String getExternalId(){
		return externalId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUpdated(String updated){
		this.updated = updated;
	}

	public String getUpdated(){
		return updated;
	}

	public void setQrString(String qrString){
		this.qrString = qrString;
	}

	public String getQrString(){
		return qrString;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseQris{" + 
			"callback_url = '" + callbackUrl + '\'' + 
			",amount = '" + amount + '\'' + 
			",metadata = '" + metadata + '\'' + 
			",created = '" + created + '\'' + 
			",external_id = '" + externalId + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",updated = '" + updated + '\'' + 
			",qr_string = '" + qrString + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}