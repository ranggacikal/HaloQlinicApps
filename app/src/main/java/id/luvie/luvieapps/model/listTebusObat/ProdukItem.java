package id.luvie.luvieapps.model.listTebusObat;

import com.google.gson.annotations.SerializedName;

public class ProdukItem{

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("img")
	private String img;

	@SerializedName("aturan")
	private String aturan;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("harga")
	private String harga;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("subtotal")
	private String subtotal;

	@SerializedName("variasi")
	private String variasi;

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

	public void setAturan(String aturan){
		this.aturan = aturan;
	}

	public String getAturan(){
		return aturan;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
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

	@Override
 	public String toString(){
		return 
			"ProdukItem{" + 
			"nama_produk = '" + namaProduk + '\'' + 
			",img = '" + img + '\'' + 
			",aturan = '" + aturan + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",harga = '" + harga + '\'' + 
			",jumlah = '" + jumlah + '\'' + 
			",subtotal = '" + subtotal + '\'' + 
			",variasi = '" + variasi + '\'' + 
			"}";
		}
}