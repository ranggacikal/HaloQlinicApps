package id.luvie.luvieapps.model.nonDokter;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseNonDokter{

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
			"ResponseNonDokter{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}