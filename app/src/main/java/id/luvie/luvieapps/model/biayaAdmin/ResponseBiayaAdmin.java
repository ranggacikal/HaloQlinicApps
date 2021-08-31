package id.luvie.luvieapps.model.biayaAdmin;

import com.google.gson.annotations.SerializedName;

public class ResponseBiayaAdmin{

	@SerializedName("biaya_admin")
	private int biayaAdmin;

	public void setBiayaAdmin(int biayaAdmin){
		this.biayaAdmin = biayaAdmin;
	}

	public int getBiayaAdmin(){
		return biayaAdmin;
	}

	@Override
 	public String toString(){
		return 
			"ResponseBiayaAdmin{" + 
			"biaya_admin = '" + biayaAdmin + '\'' + 
			"}";
		}
}