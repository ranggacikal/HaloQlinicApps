package com.haloqlinic.haloqlinicapps.model.kecamatanRajaOngkir;

import com.google.gson.annotations.SerializedName;

public class ResponseKecamatanRajaOngkir{

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
			"ResponseKecamatanRajaOngkir{" + 
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}