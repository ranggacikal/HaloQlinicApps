package id.luvie.luvieapps.model.updatePhoto;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdatePhoto{

	@SerializedName("file")
	private String file;

	@SerializedName("response")
	private String response;

	public void setFile(String file){
		this.file = file;
	}

	public String getFile(){
		return file;
	}

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseUpdatePhoto{" + 
			"file = '" + file + '\'' + 
			",response = '" + response + '\'' + 
			"}";
		}
}