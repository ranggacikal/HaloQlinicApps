package id.luvie.luvieapps.model.detailAlergiObat;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("obat")
	private String obat;

	@SerializedName("golongan")
	private String golongan;

	@SerializedName("efek")
	private String efek;

	@SerializedName("id")
	private String id;

	@SerializedName("merek")
	private String merek;

	public void setObat(String obat){
		this.obat = obat;
	}

	public String getObat(){
		return obat;
	}

	public void setGolongan(String golongan){
		this.golongan = golongan;
	}

	public String getGolongan(){
		return golongan;
	}

	public void setEfek(String efek){
		this.efek = efek;
	}

	public String getEfek(){
		return efek;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMerek(String merek){
		this.merek = merek;
	}

	public String getMerek(){
		return merek;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"obat = '" + obat + '\'' + 
			",golongan = '" + golongan + '\'' + 
			",efek = '" + efek + '\'' + 
			",id = '" + id + '\'' + 
			",merek = '" + merek + '\'' + 
			"}";
		}
}