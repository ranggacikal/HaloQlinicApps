package id.luvie.luvieapps.model.editAkun;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("usia")
	private String usia;

	@SerializedName("jk")
	private String jk;

	@SerializedName("img")
	private Object img;

	@SerializedName("kota")
	private String kota;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("nama_provinsi")
	private String namaProvinsi;

	@SerializedName("id_customer")
	private String idCustomer;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("oauthpro")
	private Object oauthpro;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama_kecamatan")
	private String namaKecamatan;

	@SerializedName("oauthid")
	private Object oauthid;

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("kecamatan")
	private String kecamatan;

	@SerializedName("nama_kota")
	private String namaKota;

	@SerializedName("email")
	private String email;

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setUsia(String usia){
		this.usia = usia;
	}

	public String getUsia(){
		return usia;
	}

	public void setJk(String jk){
		this.jk = jk;
	}

	public String getJk(){
		return jk;
	}

	public void setImg(Object img){
		this.img = img;
	}

	public Object getImg(){
		return img;
	}

	public void setKota(String kota){
		this.kota = kota;
	}

	public String getKota(){
		return kota;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setNamaProvinsi(String namaProvinsi){
		this.namaProvinsi = namaProvinsi;
	}

	public String getNamaProvinsi(){
		return namaProvinsi;
	}

	public void setIdCustomer(String idCustomer){
		this.idCustomer = idCustomer;
	}

	public String getIdCustomer(){
		return idCustomer;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setOauthpro(Object oauthpro){
		this.oauthpro = oauthpro;
	}

	public Object getOauthpro(){
		return oauthpro;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setNamaKecamatan(String namaKecamatan){
		this.namaKecamatan = namaKecamatan;
	}

	public String getNamaKecamatan(){
		return namaKecamatan;
	}

	public void setOauthid(Object oauthid){
		this.oauthid = oauthid;
	}

	public Object getOauthid(){
		return oauthid;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setKecamatan(String kecamatan){
		this.kecamatan = kecamatan;
	}

	public String getKecamatan(){
		return kecamatan;
	}

	public void setNamaKota(String namaKota){
		this.namaKota = namaKota;
	}

	public String getNamaKota(){
		return namaKota;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"provinsi = '" + provinsi + '\'' + 
			",usia = '" + usia + '\'' + 
			",jk = '" + jk + '\'' + 
			",img = '" + img + '\'' + 
			",kota = '" + kota + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",nama_provinsi = '" + namaProvinsi + '\'' + 
			",id_customer = '" + idCustomer + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",tgl_lahir = '" + tglLahir + '\'' + 
			",oauthpro = '" + oauthpro + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",nama_kecamatan = '" + namaKecamatan + '\'' + 
			",oauthid = '" + oauthid + '\'' + 
			",password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",kode = '" + kode + '\'' + 
			",kecamatan = '" + kecamatan + '\'' + 
			",nama_kota = '" + namaKota + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}