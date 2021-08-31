package id.luvie.luvieapps.model.listTebusObat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListTebusObat{

	@SerializedName("total_ongkir")
	private String totalOngkir;

	@SerializedName("data")
	private List<DataItem> data;

	public void setTotalOngkir(String totalOngkir){
		this.totalOngkir = totalOngkir;
	}

	public String getTotalOngkir(){
		return totalOngkir;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ResponseListTebusObat{" + 
			"total_ongkir = '" + totalOngkir + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}