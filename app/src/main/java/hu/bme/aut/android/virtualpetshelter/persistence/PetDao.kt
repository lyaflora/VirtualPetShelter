package hu.bme.aut.android.virtualpetshelter.persistence

import androidx.room.*
import hu.bme.aut.android.virtualpetshelter.model.Pet

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    @Query("SELECT * FROM Pet")
    suspend fun getPetList(): List<Pet>

    @Query("SELECT * FROM Pet WHERE id = :id")
    suspend fun getPet(id: Int): Pet?

    @Query("SELECT * FROM Pet WHERE name LIKE '%' || :nameSearchString || '%'")
    suspend fun getPetsByNameSearch(nameSearchString: String): List<Pet>?

    @Query("SELECT * FROM Pet WHERE type LIKE '%' || :typeSearchString || '%'")
    suspend fun getPetsByTypeSearch(typeSearchString: String): List<Pet>?

    @Query("SELECT * FROM Pet WHERE breeds_primary LIKE '%' || :breedSearchString || '%'")
    suspend fun getPetsByBreedSearch(breedSearchString: String): List<Pet>?

    @Query("SELECT * FROM Pet WHERE colors_primary = :color ")
    suspend fun getPetsByColor(color: String): List<Pet>?

    @Query("SELECT * FROM Pet WHERE type = :type")
    suspend fun getPetsByType(type: String): List<Pet>?

    @Query("SELECT DISTINCT type FROM Pet")
    suspend fun getPetTypes(): List<String>?

    @Query("SELECT DISTINCT breeds_primary FROM Pet")
    suspend fun getPetBreeds(): List<String>?

    @Query("SELECT DISTINCT colors_primary FROM Pet")
    suspend fun getPetColors(): List<String>?

    @Query("SELECT * FROM Pet WHERE gender = :gender")
    suspend fun getPetsByGender(gender: String): List<Pet>?

    @Delete
    suspend fun deletePet(pet: Pet)
}