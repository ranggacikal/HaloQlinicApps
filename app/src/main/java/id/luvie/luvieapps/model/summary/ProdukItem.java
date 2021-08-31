package id.luvie.luvieapps.model.summary;

import com.google.gson.annotations.SerializedName;

public class ProdukItem{

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("aturan")
	private String aturan;

	@SerializedName("keterangan")
	private String keterangan;

	public void setNamaProduk(String namaProduk){
		this.namaProduk = namaProduk;
	}

	public String getNamaProduk(){
		return namaProduk;
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

	@Override
 	public String toString(){
		return 
			"ProdukItem{" + 
			"nama_produk = '" + namaProduk + '\'' + 
			",aturan = '" + aturan + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			"}";
		}
}