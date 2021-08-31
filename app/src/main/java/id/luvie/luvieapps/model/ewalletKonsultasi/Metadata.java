package id.luvie.luvieapps.model.ewalletKonsultasi;

import com.google.gson.annotations.SerializedName;

public class Metadata{

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("tipe")
	private String tipe;

	@SerializedName("status")
	private String status;

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setTipe(String tipe){
		this.tipe = tipe;
	}

	public String getTipe(){
		return tipe;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Metadata{" + 
			"id_transaksi = '" + idTransaksi + '\'' + 
			",tipe = '" + tipe + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}