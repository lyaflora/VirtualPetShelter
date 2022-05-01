package hu.bme.aut.android.virtualpetshelter.persistence

import androidx.room.*
import hu.bme.aut.android.virtualpetshelter.model.Pet

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    @Query("SELECT * FROM Pet WHERE id = :id")
    suspend fun getFood(id: Int): Pet?

    @Query("SELECT * FROM Pet")
    suspend fun getPetList(): List<Pet>

    @Delete
    suspend fun deletePet(pet: Pet)
}