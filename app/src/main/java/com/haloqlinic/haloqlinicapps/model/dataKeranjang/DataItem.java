package com.haloqlinic.haloqlinicapps.model.dataKeranjang;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id_produk")
	private String idProduk;

	@SerializedName("keterangan")
	private Object keterangan;

	@SerializedName("img")
	private String img;

	@SerializedName("time_limit")
	private Object timeLimit;

	@SerializedName("id_customer")
	private String idCustomer;

	@SerializedName("id_pesan")
	private String idPesan;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("dosis")
	private Object dosis;

	@SerializedName("variasi")
	private String variasi;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("stok")
	private String stok;

	@SerializedName("berat_item")
	private String beratItem;

	@SerializedName("id_dokter")
	private Object idDokter;

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("harga")
	private String harga;

	@SerializedName("id_variasi")
	private String idVariasi;

	@SerializedName("berat")
	private String berat;

	@SerializedName("subtotal")
	private String subtotal;

	@SerializedName("kode")
	private String kode;

	@SerializedName("harga_jual")
	private String hargaJual;

	@SerializedName("status")
	private Object status;

	public void setIdProduk(String idProduk){
		this.idProduk = idProduk;
	}

	public String getIdProduk(){
		return idProduk;
	}

	public void setKeterangan(Object keterangan){
		this.keterangan = keterangan;
	}

	public Object getKeterangan(){
		return keterangan;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setTimeLimit(Object timeLimit){
		this.timeLimit = timeLimit;
	}

	public Object getTimeLimit(){
		return timeLimit;
	}

	public void setIdCustomer(String idCustomer){
		this.idCustomer = idCustomer;
	}

	public String getIdCustomer(){
		return idCustomer;
	}

	public void setIdPesan(String idPesan){
		this.idPesan = idPesan;
	}

	public String getIdPesan(){
		return idPesan;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setDosis(Object dosis){
		this.dosis = dosis;
	}

	public Object getDosis(){
		return dosis;
	}

	public void setVariasi(String variasi){
		this.variasi = variasi;
	}

	public String getVariasi(){
		return variasi;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setStok(String stok){
		this.stok = stok;
	}

	public String getStok(){
		return stok;
	}

	public void setBeratItem(String beratItem){
		this.beratItem = beratItem;
	}

	public String getBeratItem(){
		return beratItem;
	}

	public void setIdDokter(Object idDokter){
		this.idDokter = idDokter;
	}

	public Object getIdDokter(){
		return idDokter;
	}

	public void setNamaProduk(String namaProduk){
		this.namaProduk = namaProduk;
	}

	public String getNamaProduk(){
		return namaProduk;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setIdVariasi(String idVariasi){
		this.idVariasi = idVariasi;
	}

	public String getIdVariasi(){
		return idVariasi;
	}

	public void setBerat(String berat){
		this.berat = berat;
	}

	public String getBerat(){
		return berat;
	}

	public void setSubtotal(String subtotal){
		this.subtotal = subtotal;
	}

	public String getSubtotal(){
		return subtotal;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setHargaJual(String hargaJual){
		this.hargaJual = hargaJual;
	}

	public String getHargaJual(){
		return hargaJual;
	}

	public void setStatus(Object status){
		this.status = status;
	}

	public Object getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_produk = '" + idProduk + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",img = '" + img + '\'' + 
			",time_limit = '" + timeLimit + '\'' + 
			",id_customer = '" + idCustomer + '\'' + 
			",id_pesan = '" + idPesan + '\'' + 
			",id_member = '" + idMember + '\'' + 
			",dosis = '" + dosis + '\'' + 
			",variasi = '" + variasi + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			",stok = '" + stok + '\'' + 
			",berat_item = '" + beratItem + '\'' + 
			",id_dokter = '" + idDokter + '\'' + 
			",nama_produk = '" + namaProduk + '\'' + 
			",jumlah = '" + jumlah + '\'' + 
			",harga = '" + harga + '\'' + 
			",id_variasi = '" + idVariasi + '\'' + 
			",berat = '" + berat + '\'' + 
			",subtotal = '" + subtotal + '\'' + 
			",kode = '" + kode + '\'' + 
			",harga_jual = '" + hargaJual + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}