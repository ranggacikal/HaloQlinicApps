package id.luvie.luvieapps.model.updateKonsultasi;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateKonsultasi{

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
			"ResponseUpdateKonsultasi{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}