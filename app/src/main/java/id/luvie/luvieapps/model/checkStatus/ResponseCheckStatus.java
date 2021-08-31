package id.luvie.luvieapps.model.checkStatus;

import com.google.gson.annotations.SerializedName;

public class ResponseCheckStatus{

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	@SerializedName("id_dokter")
	private String idDokter;

	public void setBiaya(String biaya){
		this.biaya = biaya;
	}

	public String getBiaya(){
		return biaya;
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

	public void setIdDokter(String idDokter){
		this.idDokter = idDokter;
	}

	public String getIdDokter(){
		return idDokter;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCheckStatus{" + 
			"biaya = '" + biaya + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			"}";
		}
}