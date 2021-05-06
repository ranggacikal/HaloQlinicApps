package com.haloqlinic.haloqlinicapps.model.layananEkspedisi;

import com.google.gson.annotations.SerializedName;

public class ResponseLayananEkspedisi{

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
			"ResponseLayananEkspedisi{" + 
			"rajaongkir = '" + rajaongkir + '\'' + 
			"}";
		}
}