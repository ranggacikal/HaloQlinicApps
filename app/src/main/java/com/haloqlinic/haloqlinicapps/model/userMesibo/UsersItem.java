package com.haloqlinic.haloqlinicapps.model.userMesibo;

import com.google.gson.annotations.SerializedName;

public class UsersItem{

	@SerializedName("uid")
	private String uid;

	@SerializedName("pinned")
	private String pinned;

	@SerializedName("address")
	private String address;

	@SerializedName("flag")
	private String flag;

	@SerializedName("lastonline")
	private String lastonline;

	@SerializedName("appid")
	private String appid;

	@SerializedName("active")
	private String active;

	@SerializedName("online")
	private String online;

	@SerializedName("ipaddr")
	private String ipaddr;

	@SerializedName("token")
	private String token;

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return uid;
	}

	public void setPinned(String pinned){
		this.pinned = pinned;
	}

	public String getPinned(){
		return pinned;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setFlag(String flag){
		this.flag = flag;
	}

	public String getFlag(){
		return flag;
	}

	public void setLastonline(String lastonline){
		this.lastonline = lastonline;
	}

	public String getLastonline(){
		return lastonline;
	}

	public void setAppid(String appid){
		this.appid = appid;
	}

	public String getAppid(){
		return appid;
	}

	public void setActive(String active){
		this.active = active;
	}

	public String getActive(){
		return active;
	}

	public void setOnline(String online){
		this.online = online;
	}

	public String getOnline(){
		return online;
	}

	public void setIpaddr(String ipaddr){
		this.ipaddr = ipaddr;
	}

	public String getIpaddr(){
		return ipaddr;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"UsersItem{" + 
			"uid = '" + uid + '\'' + 
			",pinned = '" + pinned + '\'' + 
			",address = '" + address + '\'' + 
			",flag = '" + flag + '\'' + 
			",lastonline = '" + lastonline + '\'' + 
			",appid = '" + appid + '\'' + 
			",active = '" + active + '\'' + 
			",online = '" + online + '\'' + 
			",ipaddr = '" + ipaddr + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}