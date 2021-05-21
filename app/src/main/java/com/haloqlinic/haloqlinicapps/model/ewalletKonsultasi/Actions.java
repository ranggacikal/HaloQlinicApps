package com.haloqlinic.haloqlinicapps.model.ewalletKonsultasi;

import com.google.gson.annotations.SerializedName;

public class Actions{

	@SerializedName("mobile_deeplink_checkout_url")
	private Object mobileDeeplinkCheckoutUrl;

	@SerializedName("mobile_web_checkout_url")
	private String mobileWebCheckoutUrl;

	@SerializedName("qr_checkout_string")
	private Object qrCheckoutString;

	@SerializedName("desktop_web_checkout_url")
	private String desktopWebCheckoutUrl;

	public void setMobileDeeplinkCheckoutUrl(Object mobileDeeplinkCheckoutUrl){
		this.mobileDeeplinkCheckoutUrl = mobileDeeplinkCheckoutUrl;
	}

	public Object getMobileDeeplinkCheckoutUrl(){
		return mobileDeeplinkCheckoutUrl;
	}

	public void setMobileWebCheckoutUrl(String mobileWebCheckoutUrl){
		this.mobileWebCheckoutUrl = mobileWebCheckoutUrl;
	}

	public String getMobileWebCheckoutUrl(){
		return mobileWebCheckoutUrl;
	}

	public void setQrCheckoutString(Object qrCheckoutString){
		this.qrCheckoutString = qrCheckoutString;
	}

	public Object getQrCheckoutString(){
		return qrCheckoutString;
	}

	public void setDesktopWebCheckoutUrl(String desktopWebCheckoutUrl){
		this.desktopWebCheckoutUrl = desktopWebCheckoutUrl;
	}

	public String getDesktopWebCheckoutUrl(){
		return desktopWebCheckoutUrl;
	}

	@Override
 	public String toString(){
		return 
			"Actions{" + 
			"mobile_deeplink_checkout_url = '" + mobileDeeplinkCheckoutUrl + '\'' + 
			",mobile_web_checkout_url = '" + mobileWebCheckoutUrl + '\'' + 
			",qr_checkout_string = '" + qrCheckoutString + '\'' + 
			",desktop_web_checkout_url = '" + desktopWebCheckoutUrl + '\'' + 
			"}";
		}
}