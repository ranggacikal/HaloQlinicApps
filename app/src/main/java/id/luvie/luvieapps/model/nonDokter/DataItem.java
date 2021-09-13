package id.luvie.luvieapps.model.nonDokter;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("img")
	private String img;

	@SerializedName("nama")
	private String nama;

	@SerializedName("biaya")
	private String biaya;

	@SerializedName("pengalaman")
	private Object pengalaman;

	@SerializedName("jumlah_jadwal")
	private String jumlahJadwal;

	@SerializedName("id_dokter")
	private String idDokter;

	@SerializedName("status")
	private String status;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setBiaya(String biaya){
		this.biaya = biaya;
	}

	public String getBiaya(){
		return biaya;
	}

	public void setPengalaman(Object pengalaman){
		this.pengalaman = pengalaman;
	}

	public Object getPengalaman(){
		return pengalaman;
	}

	public void setJumlahJadwal(String jumlahJadwal){
		this.jumlahJadwal = jumlahJadwal;
	}

	public String getJumlahJadwal(){
		return jumlahJadwal;
	}

	public void setIdDokter(String idDokter){
		this.idDokter = idDokter;
	}

	public String getIdDokter(){
		return idDokter;
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
			"DataItem{" + 
			"img = '" + img + '\'' + 
			",nama = '" + nama + '\'' + 
			",biaya = '" + biaya + '\'' + 
			",pengalaman = '" + pengalaman + '\'' + 
			",jumlah_jadwal = '" + jumlahJadwal + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}