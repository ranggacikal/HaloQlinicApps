package com.haloqlinic.haloqlinicapps.model.provinsiRajaOngkir;

import com.google.gson.annotations.SerializedName;

public class ResponseProvinsiRajaOngkir{

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
			"ResponseProvinsiRajaOngkir{" + 
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}