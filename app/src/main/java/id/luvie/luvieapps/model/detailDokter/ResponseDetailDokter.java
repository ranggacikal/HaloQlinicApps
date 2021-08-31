package id.luvie.luvieapps.model.detailDokter;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDetailDokter{

	@SerializedName("result")
	private List<ResultItem> result;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
		return result;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDetailDokter{" + 
			"result = '" + result + '\'' + 
			"}";
		}
}