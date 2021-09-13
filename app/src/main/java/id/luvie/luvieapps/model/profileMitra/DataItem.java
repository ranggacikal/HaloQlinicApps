package id.luvie.luvieapps.model.profileMitra;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id_member")
	private String idMember;

	@SerializedName("kode")
	private String kode;

	@SerializedName("list")
	private List<ListItem> list;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setIdMember(String idMember){
		this.idMember = idMember;
	}

	public String getIdMember(){
		return idMember;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setList(List<ListItem> list){
		this.list = list;
	}

	public List<ListItem> getList(){
		return list;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_member = '" + idMember + '\'' + 
			",kode = '" + kode + '\'' + 
			",list = '" + list + '\'' + 
			",nama_toko = '" + namaToko + '\'' + 
			"}";
		}
}