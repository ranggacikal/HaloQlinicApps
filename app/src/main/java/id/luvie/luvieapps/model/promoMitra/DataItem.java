package id.luvie.luvieapps.model.promoMitra;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id_produk")
	private String idProduk;

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("img")
	private String img;

	@SerializedName("harga")
	private String harga;

	@SerializedName("harga_promo")
	private String hargaPromo;

	@SerializedName("berat")
	private String berat;

	@SerializedName("kode")
	private String kode;

	@SerializedName("id_member")
	private String idMember;

	public void setIdProduk(String idProduk){
		this.idProduk = idProduk;
	}

	public String getIdProduk(){
		return idProduk;
	}

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

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setHargaPromo(String hargaPromo){
		this.hargaPromo = hargaPromo;
	}

	public String getHargaPromo(){
		return hargaPromo;
	}

	public void setBerat(String berat){
		this.berat = berat;
	}

	public String getBerat(){
		return berat;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_produk = '" + idProduk + '\'' + 
			",nama_produk = '" + namaProduk + '\'' + 
			",img = '" + img + '\'' + 
			",harga = '" + harga + '\'' + 
			",harga_promo = '" + hargaPromo + '\'' + 
			",berat = '" + berat + '\'' + 
			",kode = '" + kode + '\'' + 
			",id_member = '" + idMember + '\'' + 
			"}";
		}
}