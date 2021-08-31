package id.luvie.luvieapps.model.daftar;

import com.google.gson.annotations.SerializedName;

public class ResponseDaftar{

	@SerializedName("response")
	private String response;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDaftar{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}