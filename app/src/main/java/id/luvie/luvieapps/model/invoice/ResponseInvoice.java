package id.luvie.luvieapps.model.invoice;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseInvoice{

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
			"ResponseInvoice{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}