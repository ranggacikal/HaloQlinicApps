package id.luvie.luvieapps.model.dokterOnTersedia;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDokterOnTersedia{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("total_page")
	private int totalPage;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setTotalPage(int totalPage){
		this.totalPage = totalPage;
	}

	public int getTotalPage(){
		return totalPage;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDokterOnTersedia{" + 
			"data = '" + data + '\'' + 
			",total_page = '" + totalPage + '\'' + 
			"}";
		}
}