package id.luvie.luvieapps.model.cekKonsultasi;

import com.google.gson.annotations.SerializedName;

public class ResponseCekKonsultasi{

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCekKonsultasi{" + 
			"id_transaksi = '" + idTransaksi + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}