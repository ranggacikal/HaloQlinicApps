package id.luvie.luvieapps.model.updateKeranjang;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateKeranjang{

	@SerializedName("response")
	private String response;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	public String getResponse(){
		return response;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}
}