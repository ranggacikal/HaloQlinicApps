package id.luvie.luvieapps.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

 class MenuHome : Serializable {
     @SerializedName("id") var id = ""
     @SerializedName("menu") var menu = ""
     @SerializedName("icon") var icon = ""
     @SerializedName("status") var status = ""
     @SerializedName("data") var items : ArrayList<MenuHome> ?= null

 }

