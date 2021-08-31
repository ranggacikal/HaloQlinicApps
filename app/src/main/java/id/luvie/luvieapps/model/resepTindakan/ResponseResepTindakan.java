package id.luvie.luvieapps.model.resepTindakan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseResepTindakan{

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
			"ResponseResepTindakan{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}