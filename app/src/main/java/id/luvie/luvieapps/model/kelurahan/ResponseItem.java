package id.luvie.luvieapps.model.kelurahan;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("subdistrict_id")
	private String subdistrictId;

	@SerializedName("village_id")
	private String villageId;

	@SerializedName("village_name")
	private String villageName;

	public void setSubdistrictId(String subdistrictId){
		this.subdistrictId = subdistrictId;
	}

	public String getSubdistrictId(){
		return subdistrictId;
	}

	public void setVillageId(String villageId){
		this.villageId = villageId;
	}

	public String getVillageId(){
		return villageId;
	}

	public void setVillageName(String villageName){
		this.villageName = villageName;
	}

	public String getVillageName(){
		return villageName;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"subdistrict_id = '" + subdistrictId + '\'' + 
			",village_id = '" + villageId + '\'' + 
			",village_name = '" + villageName + '\'' + 
			"}";
		}
}