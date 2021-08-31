package id.luvie.luvieapps.model.kategoriProduk;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKategoriProduk{

	@SerializedName("data")
	private List<DataItem> data;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ResponseKategoriProduk{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}