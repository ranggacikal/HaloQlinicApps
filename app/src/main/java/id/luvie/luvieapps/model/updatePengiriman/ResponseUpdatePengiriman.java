package id.luvie.luvieapps.model.updatePengiriman;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdatePengiriman{

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
			"ResponseUpdatePengiriman{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}