package com.haloqlinic.haloqlinicapps.model.statusTransaksiModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("produk")
	private List<ProdukItem> produk;

	@SerializedName("id_pengiriman")
	private String idPengiriman;

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("nama_usaha")
	private String namaUsaha;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("total_bayar")
	private String totalBayar;

	@SerializedName("status_pesanan")
	private String statusPesanan;

	public void setProduk(List<ProdukItem> produk){
		this.produk = produk;
	}

	public List<ProdukItem> getProduk(){
		return produk;
	}

	public void setIdPengiriman(String idPengiriman){
		this.idPengiriman = idPengiriman;
	}

	public String getIdPengiriman(){
		return idPengiriman;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setNamaUsaha(String namaUsaha){
		this.namaUsaha = namaUsaha;
	}

	public String getNamaUsaha(){
		return namaUsaha;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setTotalBayar(String totalBayar){
		this.totalBayar = totalBayar;
	}

	public String getTotalBayar(){
		return totalBayar;
	}

	public void setStatusPesanan(String statusPesanan){
		this.statusPesanan = statusPesanan;
	}

	public String getStatusPesanan(){
		return statusPesanan;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"produk = '" + produk + '\'' + 
			",id_pengiriman = '" + idPengiriman + '\'' + 
			",id_member = '" + idMember + '\'' + 
			",nama_usaha = '" + namaUsaha + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			",total_bayar = '" + totalBayar + '\'' + 
			",status_pesanan = '" + statusPesanan + '\'' + 
			"}";
		}
}