package id.luvie.luvieapps.model.detailHistory;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailHistory{

	@SerializedName("kode_bayar")
	private String kodeBayar;

	@SerializedName("tipe")
	private String tipe;

	public void setKodeBayar(String kodeBayar){
		this.kodeBayar = kodeBayar;
	}

	public String getKodeBayar(){
		return kodeBayar;
	}

	public void setTipe(String tipe){
		this.tipe = tipe;
	}

	public String getTipe(){
		return tipe;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDetailHistory{" + 
			"kode_bayar = '" + kodeBayar + '\'' + 
			",tipe = '" + tipe + '\'' + 
			"}";
		}
}