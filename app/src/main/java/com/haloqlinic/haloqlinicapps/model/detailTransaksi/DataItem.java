package com.haloqlinic.haloqlinicapps.model.detailTransaksi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("no_resi")
	private String noResi;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("nama_penerima")
	private String namaPenerima;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("kode_pos")
	private String kodePos;

	@SerializedName("kelurahan")
	private String kelurahan;

	@SerializedName("tgl_bayar")
	private Object tglBayar;

	@SerializedName("opsi_bayar")
	private String opsiBayar;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("tgl_transaksi")
	private String tglTransaksi;

	@SerializedName("city_name")
	private String cityName;

	@SerializedName("biaya_admin")
	private String biayaAdmin;

	@SerializedName("province")
	private String province;

	@SerializedName("produk")
	private List<ProdukItem> produk;

	@SerializedName("tgl_kirim")
	private Object tglKirim;

	@SerializedName("jumlah_bayar")
	private String jumlahBayar;

	@SerializedName("subdistrict_name")
	private String subdistrictName;

	public void setNoResi(String noResi){
		this.noResi = noResi;
	}

	public String getNoResi(){
		return noResi;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setNamaPenerima(String namaPenerima){
		this.namaPenerima = namaPenerima;
	}

	public String getNamaPenerima(){
		return namaPenerima;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setKodePos(String kodePos){
		this.kodePos = kodePos;
	}

	public String getKodePos(){
		return kodePos;
	}

	public void setKelurahan(String kelurahan){
		this.kelurahan = kelurahan;
	}

	public String getKelurahan(){
		return kelurahan;
	}

	public void setTglBayar(Object tglBayar){
		this.tglBayar = tglBayar;
	}

	public Object getTglBayar(){
		return tglBayar;
	}

	public void setOpsiBayar(String opsiBayar){
		this.opsiBayar = opsiBayar;
	}

	public String getOpsiBayar(){
		return opsiBayar;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	public void setTglTransaksi(String tglTransaksi){
		this.tglTransaksi = tglTransaksi;
	}

	public String getTglTransaksi(){
		return tglTransaksi;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return cityName;
	}

	public void setBiayaAdmin(String biayaAdmin){
		this.biayaAdmin = biayaAdmin;
	}

	public String getBiayaAdmin(){
		return biayaAdmin;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public void setProduk(List<ProdukItem> produk){
		this.produk = produk;
	}

	public List<ProdukItem> getProduk(){
		return produk;
	}

	public void setTglKirim(Object tglKirim){
		this.tglKirim = tglKirim;
	}

	public Object getTglKirim(){
		return tglKirim;
	}

	public void setJumlahBayar(String jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public String getJumlahBayar(){
		return jumlahBayar;
	}

	public void setSubdistrictName(String subdistrictName){
		this.subdistrictName = subdistrictName;
	}

	public String getSubdistrictName(){
		return subdistrictName;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"no_resi = '" + noResi + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",nama_penerima = '" + namaPenerima + '\'' + 
			",id_member = '" + idMember + '\'' + 
			",kode_pos = '" + kodePos + '\'' + 
			",kelurahan = '" + kelurahan + '\'' + 
			",tgl_bayar = '" + tglBayar + '\'' + 
			",opsi_bayar = '" + opsiBayar + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			",tgl_transaksi = '" + tglTransaksi + '\'' + 
			",city_name = '" + cityName + '\'' + 
			",biaya_admin = '" + biayaAdmin + '\'' + 
			",province = '" + province + '\'' + 
			",produk = '" + produk + '\'' + 
			",tgl_kirim = '" + tglKirim + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",subdistrict_name = '" + subdistrictName + '\'' + 
			"}";
		}
}