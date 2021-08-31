package id.luvie.luvieapps.model.detailArtikel;

import com.google.gson.annotations.SerializedName;

public class ListItem{

	@SerializedName("images")
	private String images;

	public void setImages(String images){
		this.images = images;
	}

	public String getImages(){
		return images;
	}

	@Override
 	public String toString(){
		return 
			"ListItem{" + 
			"images = '" + images + '\'' + 
			"}";
		}
}