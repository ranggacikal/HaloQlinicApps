package id.luvie.luvieapps.model.loginmesibo;

import com.google.gson.annotations.SerializedName;

public class App{

	@SerializedName("server")
	private String server;

	@SerializedName("u_users")
	private String uUsers;

	@SerializedName("flag")
	private String flag;

	@SerializedName("apn_info")
	private String apnInfo;

	@SerializedName("pushflags")
	private String pushflags;

	@SerializedName("fcm_key")
	private String fcmKey;

	@SerializedName("u_groups")
	private String uGroups;

	@SerializedName("secret")
	private String secret;

	@SerializedName("nrate")
	private String nrate;

	@SerializedName("url")
	private String url;

	@SerializedName("notify")
	private String notify;

	@SerializedName("token")
	private String token;

	@SerializedName("uid")
	private String uid;

	@SerializedName("ninterval")
	private String ninterval;

	@SerializedName("uts")
	private String uts;

	@SerializedName("name")
	private String name;

	@SerializedName("d_storage")
	private String dStorage;

	@SerializedName("aid")
	private String aid;

	@SerializedName("f_beta")
	private String fBeta;

	@SerializedName("fcm_id")
	private String fcmId;

	@SerializedName("ts")
	private String ts;

	public void setServer(String server){
		this.server = server;
	}

	public String getServer(){
		return server;
	}

	public void setUUsers(String uUsers){
		this.uUsers = uUsers;
	}

	public String getUUsers(){
		return uUsers;
	}

	public void setFlag(String flag){
		this.flag = flag;
	}

	public String getFlag(){
		return flag;
	}

	public void setApnInfo(String apnInfo){
		this.apnInfo = apnInfo;
	}

	public String getApnInfo(){
		return apnInfo;
	}

	public void setPushflags(String pushflags){
		this.pushflags = pushflags;
	}

	public String getPushflags(){
		return pushflags;
	}

	public void setFcmKey(String fcmKey){
		this.fcmKey = fcmKey;
	}

	public String getFcmKey(){
		return fcmKey;
	}

	public void setUGroups(String uGroups){
		this.uGroups = uGroups;
	}

	public String getUGroups(){
		return uGroups;
	}

	public void setSecret(String secret){
		this.secret = secret;
	}

	public String getSecret(){
		return secret;
	}

	public void setNrate(String nrate){
		this.nrate = nrate;
	}

	public String getNrate(){
		return nrate;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setNotify(String notify){
		this.notify = notify;
	}

	public String getNotify(){
		return notify;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return uid;
	}

	public void setNinterval(String ninterval){
		this.ninterval = ninterval;
	}

	public String getNinterval(){
		return ninterval;
	}

	public void setUts(String uts){
		this.uts = uts;
	}

	public String getUts(){
		return uts;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDStorage(String dStorage){
		this.dStorage = dStorage;
	}

	public String getDStorage(){
		return dStorage;
	}

	public void setAid(String aid){
		this.aid = aid;
	}

	public String getAid(){
		return aid;
	}

	public void setFBeta(String fBeta){
		this.fBeta = fBeta;
	}

	public String getFBeta(){
		return fBeta;
	}

	public void setFcmId(String fcmId){
		this.fcmId = fcmId;
	}

	public String getFcmId(){
		return fcmId;
	}

	public void setTs(String ts){
		this.ts = ts;
	}

	public String getTs(){
		return ts;
	}

	@Override
 	public String toString(){
		return 
			"App{" + 
			"server = '" + server + '\'' + 
			",u_users = '" + uUsers + '\'' + 
			",flag = '" + flag + '\'' + 
			",apn_info = '" + apnInfo + '\'' + 
			",pushflags = '" + pushflags + '\'' + 
			",fcm_key = '" + fcmKey + '\'' + 
			",u_groups = '" + uGroups + '\'' + 
			",secret = '" + secret + '\'' + 
			",nrate = '" + nrate + '\'' + 
			",url = '" + url + '\'' + 
			",notify = '" + notify + '\'' + 
			",token = '" + token + '\'' + 
			",uid = '" + uid + '\'' + 
			",ninterval = '" + ninterval + '\'' + 
			",uts = '" + uts + '\'' + 
			",name = '" + name + '\'' + 
			",d_storage = '" + dStorage + '\'' + 
			",aid = '" + aid + '\'' + 
			",f_beta = '" + fBeta + '\'' + 
			",fcm_id = '" + fcmId + '\'' + 
			",ts = '" + ts + '\'' + 
			"}";
		}
}