package com.haloqlinic.haloqlinicapps.model.historyTransaksi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("produk")
	private List<ProdukItem> produk;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("jumlah_bayar")
	private String jumlahBayar;

	@SerializedName("status_pesanan")
	private String statusPesanan;

	public void setProduk(List<ProdukItem> produk){
		this.produk = produk;
	}

	public List<ProdukItem> getProduk(){
		return produk;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setJumlahBayar(String jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public String getJumlahBayar(){
		return jumlahBayar;
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
			",id_transaksi = '" + idTransaksi + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",status_pesanan = '" + statusPesanan + '\'' + 
			"}";
		}
}