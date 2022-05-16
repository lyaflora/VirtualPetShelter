package hu.bme.aut.android.virtualpetshelter.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Links (

  @Embedded(prefix = "self_")
  @SerializedName("self"         ) var self         : Self?         = Self(),
  @Embedded(prefix = "type_")
  @SerializedName("type"         ) var type         : Type?         = Type(),
  @Embedded(prefix = "organization_")
  @SerializedName("organization" ) var organization : Organization? = Organization()

)