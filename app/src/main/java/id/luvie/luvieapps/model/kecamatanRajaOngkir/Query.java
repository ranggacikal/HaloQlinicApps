package id.luvie.luvieapps.model.kecamatanRajaOngkir;

import com.google.gson.annotations.SerializedName;

public class Query{

	@SerializedName("city")
	private String city;

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"city = '" + city + '\'' + 
			"}";
		}
}