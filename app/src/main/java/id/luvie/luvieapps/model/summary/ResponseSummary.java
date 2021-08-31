package id.luvie.luvieapps.model.summary;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSummary{

	@SerializedName("img")
	private String img;

	@SerializedName("nama")
	private String nama;

	@SerializedName("produk")
	private List<ProdukItem> produk;

	@SerializedName("mulai_konsultasi")
	private String mulaiKonsultasi;

	@SerializedName("nama_dokter")
	private String namaDokter;

	@SerializedName("diagnosis")
	private String diagnosis;

	@SerializedName("catatan")
	private String catatan;

	@SerializedName("spesialis")
	private String spesialis;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("rilis_resep")
	private String rilisResep;

	@SerializedName("batas_konsultasi")
	private String batasKonsultasi;

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

	public void setProduk(List<ProdukItem> produk){
		this.produk = produk;
	}

	public List<ProdukItem> getProduk(){
		return produk;
	}

	public void setMulaiKonsultasi(String mulaiKonsultasi){
		this.mulaiKonsultasi = mulaiKonsultasi;
	}

	public String getMulaiKonsultasi(){
		return mulaiKonsultasi;
	}

	public void setNamaDokter(String namaDokter){
		this.namaDokter = namaDokter;
	}

	public String getNamaDokter(){
		return namaDokter;
	}

	public void setDiagnosis(String diagnosis){
		this.diagnosis = diagnosis;
	}

	public String getDiagnosis(){
		return diagnosis;
	}

	public void setCatatan(String catatan){
		this.catatan = catatan;
	}

	public String getCatatan(){
		return catatan;
	}

	public void setSpesialis(String spesialis){
		this.spesialis = spesialis;
	}

	public String getSpesialis(){
		return spesialis;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setRilisResep(String rilisResep){
		this.rilisResep = rilisResep;
	}

	public String getRilisResep(){
		return rilisResep;
	}

	public void setBatasKonsultasi(String batasKonsultasi){
		this.batasKonsultasi = batasKonsultasi;
	}

	public String getBatasKonsultasi(){
		return batasKonsultasi;
	}

	@Override
 	public String toString(){
		return 
			"ResponseSummary{" + 
			"img = '" + img + '\'' + 
			",nama = '" + nama + '\'' + 
			",produk = '" + produk + '\'' + 
			",mulai_konsultasi = '" + mulaiKonsultasi + '\'' + 
			",nama_dokter = '" + namaDokter + '\'' + 
			",diagnosis = '" + diagnosis + '\'' + 
			",catatan = '" + catatan + '\'' + 
			",spesialis = '" + spesialis + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			",rilis_resep = '" + rilisResep + '\'' + 
			",batas_konsultasi = '" + batasKonsultasi + '\'' + 
			"}";
		}
}