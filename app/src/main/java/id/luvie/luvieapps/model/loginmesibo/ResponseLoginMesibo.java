package id.luvie.luvieapps.model.loginmesibo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginMesibo{

	@SerializedName("app")
	private App app;

	@SerializedName("result")
	private boolean result;

	@SerializedName("op")
	private String op;

	@SerializedName("uts")
	private String uts;

	@SerializedName("userscount")
	private int userscount;

	@SerializedName("disabled")
	private int disabled;

	@SerializedName("ipaddr")
	private String ipaddr;

	@SerializedName("users")
	private List<UsersItem> users;

	public void setApp(App app){
		this.app = app;
	}

	public App getApp(){
		return app;
	}

	public void setResult(boolean result){
		this.result = result;
	}

	public boolean isResult(){
		return result;
	}

	public void setOp(String op){
		this.op = op;
	}

	public String getOp(){
		return op;
	}

	public void setUts(String uts){
		this.uts = uts;
	}

	public String getUts(){
		return uts;
	}

	public void setUserscount(int userscount){
		this.userscount = userscount;
	}

	public int getUserscount(){
		return userscount;
	}

	public void setDisabled(int disabled){
		this.disabled = disabled;
	}

	public int getDisabled(){
		return disabled;
	}

	public void setIpaddr(String ipaddr){
		this.ipaddr = ipaddr;
	}

	public String getIpaddr(){
		return ipaddr;
	}

	public void setUsers(List<UsersItem> users){
		this.users = users;
	}

	public List<UsersItem> getUsers(){
		return users;
	}

	@Override
 	public String toString(){
		return 
			"ResponseLoginMesibo{" + 
			"app = '" + app + '\'' + 
			",result = '" + result + '\'' + 
			",op = '" + op + '\'' + 
			",uts = '" + uts + '\'' + 
			",userscount = '" + userscount + '\'' + 
			",disabled = '" + disabled + '\'' + 
			",ipaddr = '" + ipaddr + '\'' + 
			",users = '" + users + '\'' + 
			"}";
		}
}