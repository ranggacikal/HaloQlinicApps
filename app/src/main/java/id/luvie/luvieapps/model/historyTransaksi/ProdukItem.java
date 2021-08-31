package id.luvie.luvieapps.model.historyTransaksi;

import com.google.gson.annotations.SerializedName;

public class ProdukItem{

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("img")
	private String img;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("harga")
	private String harga;

	@SerializedName("subtotal")
	private String subtotal;

	@SerializedName("variasi")
	private String variasi;

	@SerializedName("nama_usaha")
	private String namaUsaha;

	public void setNamaProduk(String namaProduk){
		this.namaProduk = namaProduk;
	}

	public String getNamaProduk(){
		return namaProduk;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
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

	public void setSubtotal(String subtotal){
		this.subtotal = subtotal;
	}

	public String getSubtotal(){
		return subtotal;
	}

	public void setVariasi(String variasi){
		this.variasi = variasi;
	}

	public String getVariasi(){
		return variasi;
	}

	public void setNamaUsaha(String namaUsaha){
		this.namaUsaha = namaUsaha;
	}

	public String getNamaUsaha(){
		return namaUsaha;
	}

	@Override
 	public String toString(){
		return 
			"ProdukItem{" + 
			"nama_produk = '" + namaProduk + '\'' + 
			",img = '" + img + '\'' + 
			",jumlah = '" + jumlah + '\'' + 
			",harga = '" + harga + '\'' + 
			",subtotal = '" + subtotal + '\'' + 
			",variasi = '" + variasi + '\'' + 
			",nama_usaha = '" + namaUsaha + '\'' + 
			"}";
		}
}