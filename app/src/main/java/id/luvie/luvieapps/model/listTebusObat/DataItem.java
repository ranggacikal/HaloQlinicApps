package id.luvie.luvieapps.model.listTebusObat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("layanan_kurir")
	private String layananKurir;

	@SerializedName("kota")
	private String kota;

	@SerializedName("ongkir")
	private String ongkir;

	@SerializedName("produk")
	private List<ProdukItem> produk;

	@SerializedName("total_belanja")
	private String totalBelanja;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("total_berat")
	private String totalBerat;

	@SerializedName("ekspedisi")
	private String ekspedisi;

	@SerializedName("kurir")
	private String kurir;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setLayananKurir(String layananKurir){
		this.layananKurir = layananKurir;
	}

	public String getLayananKurir(){
		return layananKurir;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setOngkir(String ongkir){
		this.ongkir = ongkir;
	}

	public String getOngkir(){
		return ongkir;
	}

	public void setProduk(List<ProdukItem> produk){
		this.produk = produk;
	}

	public List<ProdukItem> getProduk(){
		return produk;
	}

	public void setTotalBelanja(String totalBelanja){
		this.totalBelanja = totalBelanja;
	}

	public String getTotalBelanja(){
		return totalBelanja;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setKecamatan(String kecamatan){
		this.kecamatan = kecamatan;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public void setTotalBerat(String totalBerat){
		this.totalBerat = totalBerat;
	}

	public String getTotalBerat(){
		return totalBerat;
	}

	public void setEkspedisi(String ekspedisi){
		this.ekspedisi = ekspedisi;
	}

	public String getEkspedisi(){
		return ekspedisi;
	}

	public void setKurir(String kurir){
		this.kurir = kurir;
	}

	public String getKurir(){
		return kurir;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"provinsi = '" + provinsi + '\'' + 
			",layanan_kurir = '" + layananKurir + '\'' + 
			",kota = '" + kota + '\'' + 
			",ongkir = '" + ongkir + '\'' + 
			",produk = '" + produk + '\'' + 
			",total_belanja = '" + totalBelanja + '\'' + 
			",id_member = '" + idMember + '\'' + 
			",kecamatan = '" + kecamatan + '\'' + 
			",total_berat = '" + totalBerat + '\'' + 
			",ekspedisi = '" + ekspedisi + '\'' + 
			",kurir = '" + kurir + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			"}";
		}
}