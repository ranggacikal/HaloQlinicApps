package id.luvie.luvieapps.model.listAlergiObat;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("obat")
	private String obat;

	@SerializedName("id")
	private String id;

	public void setObat(String obat){
		this.obat = obat;
	}

	public String getObat(){
		return obat;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"obat = '" + obat + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}