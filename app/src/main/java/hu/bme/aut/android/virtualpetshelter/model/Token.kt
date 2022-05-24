package hu.bme.aut.android.virtualpetshelter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Token (

    @SerializedName("token_type"   ) var tokenType   : String? = null,
    @SerializedName("expires_in"   ) var expiresIn   : Int?    = null,
    @PrimaryKey
    @SerializedName("access_token" ) var accessToken : String? = null

)