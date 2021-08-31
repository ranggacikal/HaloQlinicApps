package id.luvie.luvieapps.model.invoiceKonsultasiQR;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("biaya_admin")
	private String biayaAdmin;

	@SerializedName("id_opsi_bayar")
	private String idOpsiBayar;

	@SerializedName("jumlah_bayar")
	private String jumlahBayar;

	@SerializedName("opsi_bayar")
	private String opsiBayar;

	public void setBiaya(String biaya){
		this.biaya = biaya;
	}

	public String getBiaya(){
		return biaya;
	}

	public void setBiayaAdmin(String biayaAdmin){
		this.biayaAdmin = biayaAdmin;
	}

	public String getBiayaAdmin(){
		return biayaAdmin;
	}

	public void setIdOpsiBayar(String idOpsiBayar){
		this.idOpsiBayar = idOpsiBayar;
	}

	public String getIdOpsiBayar(){
		return idOpsiBayar;
	}

	public void setJumlahBayar(String jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public String getJumlahBayar(){
		return jumlahBayar;
	}

	public void setOpsiBayar(String opsiBayar){
		this.opsiBayar = opsiBayar;
	}

	public String getOpsiBayar(){
		return opsiBayar;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"biaya = '" + biaya + '\'' + 
			",biaya_admin = '" + biayaAdmin + '\'' + 
			",id_opsi_bayar = '" + idOpsiBayar + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",opsi_bayar = '" + opsiBayar + '\'' + 
			"}";
		}
}