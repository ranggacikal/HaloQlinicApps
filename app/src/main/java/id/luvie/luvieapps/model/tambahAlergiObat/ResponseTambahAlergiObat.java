package id.luvie.luvieapps.model.tambahAlergiObat;

import com.google.gson.annotations.SerializedName;

public class ResponseTambahAlergiObat{

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
			"ResponseTambahAlergiObat{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}