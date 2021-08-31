package id.luvie.luvieapps.model.ewallet;

import com.google.gson.annotations.SerializedName;

public class ChannelProperties{

	@SerializedName("success_redirect_url")
	private String successRedirectUrl;

	public void setSuccessRedirectUrl(String successRedirectUrl){
		this.successRedirectUrl = successRedirectUrl;
	}

	public String getSuccessRedirectUrl(){
		return successRedirectUrl;
	}

	@Override
 	public String toString(){
		return 
			"ChannelProperties{" + 
			"success_redirect_url = '" + successRedirectUrl + '\'' + 
			"}";
		}
}