package id.luvie.luvieapps.model.logingoogle;

import com.google.gson.annotations.SerializedName;

public class ResponseLoginGoogle{

	@SerializedName("response")
	private Response response;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLoginGoogle{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}