package id.luvie.luvieapps.model.profileMitra;

import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("nama_produk")
	private String namaProduk;

	public void setNamaProduk(String namaProduk){
		this.namaProduk = namaProduk;
	}

	public String getNamaProduk(){
		return namaProduk;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"nama_produk = '" + namaProduk + '\'' + 
			"}";
		}
}