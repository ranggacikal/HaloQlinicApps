package id.luvie.luvieapps.model.kebijakanPrivasi;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("teks")
	private String teks;

	public void setTeks(String teks){
		this.teks = teks;
	}

	public String getTeks(){
		return teks;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"teks = '" + teks + '\'' + 
			"}";
		}
}