package hu.bme.aut.android.virtualpetshelter.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Contact (

  @SerializedName("email"   ) var email   : String?  = null,
  @SerializedName("phone"   ) var phone   : String?  = null,
  @Embedded
  @SerializedName("address" ) var address : Address? = Address()

)