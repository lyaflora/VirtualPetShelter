package hu.bme.aut.android.virtualpetshelter.model

import com.google.gson.annotations.SerializedName

data class Attributes (

  @SerializedName("spayed_neutered" ) var spayedNeutered : Boolean? = null,
  @SerializedName("house_trained"   ) var houseTrained   : Boolean? = null,
  @SerializedName("declawed"        ) var declawed       : Boolean? = null,
  @SerializedName("special_needs"   ) var specialNeeds   : Boolean? = null,
  @SerializedName("shots_current"   ) var shotsCurrent   : Boolean? = null

)