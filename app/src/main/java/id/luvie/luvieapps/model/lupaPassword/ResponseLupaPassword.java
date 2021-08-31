package id.luvie.luvieapps.model.lupaPassword;

import com.google.gson.annotations.SerializedName;

public class ResponseLupaPassword{

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
			"ResponseLupaPassword{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}