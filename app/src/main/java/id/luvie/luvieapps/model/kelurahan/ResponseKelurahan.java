package id.luvie.luvieapps.model.kelurahan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKelurahan{

	@SerializedName("response")
	private List<ResponseItem> response;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKelurahan{" + 
			"response = '" + response + '\'' + 
			"}";
		}
}