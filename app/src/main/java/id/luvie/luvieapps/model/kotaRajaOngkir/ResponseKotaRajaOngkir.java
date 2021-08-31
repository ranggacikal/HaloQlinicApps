package id.luvie.luvieapps.model.kotaRajaOngkir;

import com.google.gson.annotations.SerializedName;

public class ResponseKotaRajaOngkir{

	@SerializedName("rajaongkir")
	private Rajaongkir rajaongkir;

	public void setRajaongkir(Rajaongkir rajaongkir){
		this.rajaongkir = rajaongkir;
	}

	public Rajaongkir getRajaongkir(){
		return rajaongkir;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKotaRajaOngkir{" + 
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}