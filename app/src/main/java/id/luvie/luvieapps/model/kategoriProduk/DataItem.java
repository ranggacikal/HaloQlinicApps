package id.luvie.luvieapps.model.kategoriProduk;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("color")
	private String color;

	@SerializedName("icon")
	private String icon;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("kategori")
	private String kategori;

	public void setIdKategori(String idKategori){
		this.idKategori = idKategori;
	}

	public String getIdKategori(){
		return idKategori;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setKategori(String kategori){
		this.kategori = kategori;
	}

	public String getKategori(){
		return kategori;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_kategori = '" + idKategori + '\'' + 
			",color = '" + color + '\'' + 
			",icon = '" + icon + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",kategori = '" + kategori + '\'' + 
			"}";
		}
}