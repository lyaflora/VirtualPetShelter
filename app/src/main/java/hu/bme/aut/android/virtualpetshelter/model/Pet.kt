package hu.bme.aut.android.virtualpetshelter.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pet(
    @PrimaryKey val id: Int,
    val name: String?,
    val type: String?,
    val breed: String?,
    val color: String?,
    val shelterName: String?,
    val shelterWebsite: String?,
    val contactName: String?
)