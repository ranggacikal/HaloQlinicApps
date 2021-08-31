package id.luvie.luvieapps.model.getPlayerId;

import com.google.gson.annotations.SerializedName;

public class ResponseGetPlayerId{

	@SerializedName("player_id")
	private String playerId;

	@SerializedName("id_customer")
	private String idCustomer;

	public void setPlayerId(String playerId){
		this.playerId = playerId;
	}

	public String getPlayerId(){
		return playerId;
	}

	public void setIdCustomer(String idCustomer){
		this.idCustomer = idCustomer;
	}

	public String getIdCustomer(){
		return idCustomer;
	}

	@Override
 	public String toString(){
		return 
			"ResponseGetPlayerId{" + 
			"player_id = '" + playerId + '\'' + 
			",id_customer = '" + idCustomer + '\'' + 
			"}";
		}
}