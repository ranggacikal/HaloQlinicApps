package id.luvie.luvieapps.model.layananEkspedisi;

import com.google.gson.annotations.SerializedName;

public class DestinationDetails{

	@SerializedName("subdistrict_id")
	private String subdistrictId;

	@SerializedName("province")
	private String province;

	@SerializedName("province_id")
	private String provinceId;

	@SerializedName("city")
	private String city;

	@SerializedName("type")
	private String type;

	@SerializedName("subdistrict_name")
	private String subdistrictName;

	@SerializedName("city_id")
	private String cityId;

	public void setSubdistrictId(String subdistrictId){
		this.subdistrictId = subdistrictId;
	}

	public String getSubdistrictId(){
		return subdistrictId;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}

	public String getProvinceId(){
		return provinceId;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setSubdistrictName(String subdistrictName){
		this.subdistrictName = subdistrictName;
	}

	public String getSubdistrictName(){
		return subdistrictName;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"DestinationDetails{" + 
			"subdistrict_id = '" + subdistrictId + '\'' + 
			",province = '" + province + '\'' + 
			",province_id = '" + provinceId + '\'' + 
			",city = '" + city + '\'' + 
			",type = '" + type + '\'' + 
			",subdistrict_name = '" + subdistrictName + '\'' + 
			",city_id = '" + cityId + '\'' + 
			"}";
		}
}