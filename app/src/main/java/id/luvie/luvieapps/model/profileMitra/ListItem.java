package id.luvie.luvieapps.model.profileMitra;

import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("treatment")
	private String treatment;

	public void setTreatment(String treatment){
		this.treatment = treatment;
	}

	public String getTreatment(){
		return treatment;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"treatment = '" + treatment + '\'' + 
			"}";
		}
}