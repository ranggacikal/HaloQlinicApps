package id.luvie.luvieapps.model.tambahKeranjang;

import com.google.gson.annotations.SerializedName;

public class ResponseTambahKeranjang{

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
			"ResponseTambahKeranjang{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}