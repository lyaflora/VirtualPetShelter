package hu.bme.aut.android.virtualpetshelter.model

import com.google.gson.annotations.SerializedName

data class PetListResponse (

    @SerializedName("animals"    ) var petList      : List<Pet>,
    @SerializedName("pagination" ) var pagination   : Pagination?   = Pagination()

)