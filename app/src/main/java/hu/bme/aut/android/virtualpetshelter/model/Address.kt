package hu.bme.aut.android.virtualpetshelter.model

import com.google.gson.annotations.SerializedName

data class Address (

  @SerializedName("address1" ) var address1 : String? = null,
  @SerializedName("address2" ) var address2 : String? = null,
  @SerializedName("city"     ) var city     : String? = null,
  @SerializedName("state"    ) var state    : String? = null,
  @SerializedName("postcode" ) var postcode : String? = null,
  @SerializedName("country"  ) var country  : String? = null

)