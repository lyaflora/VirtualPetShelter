package hu.bme.aut.android.virtualpetshelter.model

import com.google.gson.annotations.SerializedName

data class Environment (

  @SerializedName("children" ) var children : Boolean? = null,
  @SerializedName("dogs"     ) var dogs     : String?  = null,
  @SerializedName("cats"     ) var cats     : Boolean? = null

)