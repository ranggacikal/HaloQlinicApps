package id.luvie.luvieapps.model.checkRecipe;

import com.google.gson.annotations.SerializedName;

public class ResponseCheckRecipe{

	@SerializedName("produk")
	private int produk;

	@SerializedName("tindakan")
	private int tindakan;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	public void setProduk(int produk){
		this.produk = produk;
	}

	public int getProduk(){
		return produk;
	}

	public void setTindakan(int tindakan){
		this.tindakan = tindakan;
	}

	public int getTindakan(){
		return tindakan;
	}

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCheckRecipe{" + 
			"produk = '" + produk + '\'' + 
			",tindakan = '" + tindakan + '\'' + 
			",id_transaksi = '" + idTransaksi + '\'' + 
			"}";
		}
}