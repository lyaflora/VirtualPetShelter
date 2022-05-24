package hu.bme.aut.android.virtualpetshelter.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pets")
data class Pet (
    @PrimaryKey
    @SerializedName("id"                     ) var id                   : Int?,
    @SerializedName("organization_id"        ) var organizationId       : String?,
    @SerializedName("url"                    ) var url                  : String?,
    @SerializedName("type"                   ) var type                 : String?,
    @SerializedName("species"                ) var species              : String?,
    @Embedded(prefix = "breeds_")
    @SerializedName("breeds"                 ) var breeds               : Breeds?,
    @Embedded(prefix = "colors_")
    @SerializedName("colors"                 ) var colors               : Colors?,
    @SerializedName("age"                    ) var age                  : String?,
    @SerializedName("gender"                 ) var gender               : String?,
    @SerializedName("size"                   ) var size                 : String?,
    @SerializedName("coat"                   ) var coat                 : String?,
    @Embedded
    @SerializedName("attributes"             ) var attributes           : Attributes?,
    @Embedded
    @SerializedName("environment"            ) var environment          : Environment?,
    @SerializedName("tags"                   ) var tags                 : List<String>,
    @SerializedName("name"                   ) var name                 : String?,
    @SerializedName("description"            ) var description          : String?,
    @SerializedName("organization_animal_id" ) var organizationAnimalId : String?,
    @SerializedName("photos"                 ) var photos               : List<Photos>,
    @Embedded(prefix = "primary_photo_cropped_")
    @SerializedName("primary_photo_cropped"  ) var primaryPhotoCropped  : PrimaryPhotoCropped?,
    @Ignore
    @SerializedName("videos"                 ) var videos               : List<Videos>,
    @SerializedName("status"                 ) var status               : String?,
    @SerializedName("status_changed_at"      ) var statusChangedAt      : String?,
    @SerializedName("published_at"           ) var publishedAt          : String?,
    @SerializedName("distance"               ) var distance             : String?,
    @Embedded
    @SerializedName("contact"                ) var contact              : Contact?,
    @Embedded
    @SerializedName("links"                 ) var Links                : Links?

) {
    constructor() : this (null,
        null,
        null,
        null,
        null,
        Breeds(),
        Colors(),
        null,
        null,
        null,
        null,
        Attributes(),
        Environment(),
        listOf(),
        null,
        null,
        null,
        listOf(),
        PrimaryPhotoCropped(),
        listOf(),
        null,
        null,
        null,
        null,
        Contact(),
        Links())
}