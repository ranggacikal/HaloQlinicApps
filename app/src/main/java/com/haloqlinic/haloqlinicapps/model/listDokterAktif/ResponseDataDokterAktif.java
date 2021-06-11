package com.haloqlinic.haloqlinicapps.model.listDokterAktif;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataDokterAktif{

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
			"ResponseDataDokterAktif{" + 
			"data = '" + data + '\'' + 
			",total_page = '" + totalPage + '\'' + 
			"}";
		}
}