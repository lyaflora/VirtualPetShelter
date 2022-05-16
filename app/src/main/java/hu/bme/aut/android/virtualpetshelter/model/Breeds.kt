package hu.bme.aut.android.virtualpetshelter.model

import com.google.gson.annotations.SerializedName

data class Breeds (

  @SerializedName("primary"   ) var primary   : String?  = null,
  @SerializedName("secondary" ) var secondary : String?  = null,
  @SerializedName("mixed"     ) var mixed     : Boolean? = null,
  @SerializedName("unknown"   ) var unknown   : Boolean? = null

)