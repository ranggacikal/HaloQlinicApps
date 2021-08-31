package id.luvie.luvieapps.model.batalkanKonsultasi;

import com.google.gson.annotations.SerializedName;

public class ResponseBatalkanKonsultasi{

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
			"ResponseBatalkanKonsultasi{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}