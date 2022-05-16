package hu.bme.aut.android.virtualpetshelter.model

import com.google.gson.annotations.SerializedName

data class PrimaryPhotoCropped (

  @SerializedName("small"  ) var small  : String? = null,
  @SerializedName("medium" ) var medium : String? = null,
  @SerializedName("large"  ) var large  : String? = null,
  @SerializedName("full"   ) var full   : String? = null

)