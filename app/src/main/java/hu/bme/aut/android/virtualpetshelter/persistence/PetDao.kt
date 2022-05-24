package hu.bme.aut.android.virtualpetshelter.persistence

import androidx.room.*
import hu.bme.aut.android.virtualpetshelter.model.Pet

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(petList: List<Pet>)

    @Query("SELECT * FROM pets")
    suspend fun getPetList(): List<Pet>

    @Query("SELECT * FROM pets WHERE primary_photo_cropped_small IS NOT NULL")
    suspend fun getPetListWithPhotos(): List<Pet>?

    @Query("DELETE FROM pets WHERE primary_photo_cropped_small IS NULL")
    suspend fun deletePetsWithoutPicture()

    @Query("SELECT * FROM pets WHERE id = :id")
    suspend fun getPet(id: Int): Pet?

    @Query("SELECT * FROM pets WHERE name LIKE '%' || :nameSearchString || '%'")
    suspend fun getPetsByNameSearch(nameSearchString: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE type LIKE '%' || :typeSearchString || '%'")
    suspend fun getPetsByTypeSearch(typeSearchString: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE breeds_primary LIKE '%' || :breedSearchString || '%'")
    suspend fun getPetsByBreedSearch(breedSearchString: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE colors_primary = :color ")
    suspend fun getPetsByColor(color: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE type = :type")
    suspend fun getPetsByType(type: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE breeds_primary = :breed")
    suspend fun getPetsByBreed(breed: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE type = :type AND breeds_primary = :breed")
    suspend fun getPetsByTypeAndBreed(type: String, breed: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE type = :type AND gender = :gender")
    suspend fun getPetsByTypeAndGender(type: String, gender: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE breeds_primary = :breed AND gender = :gender")
    suspend fun getPetsByBreedAndGender(breed: String, gender: String): List<Pet>?

    @Query("SELECT * FROM pets WHERE type = :type AND breeds_primary = :breed AND gender = :gender")
    suspend fun getPetsByTypeAndBreedAndGender(type: String, breed: String, gender: String): List<Pet>?

    @Query("SELECT DISTINCT type FROM pets")
    suspend fun getPetTypes(): List<String>?

    @Query("SELECT DISTINCT breeds_primary FROM pets")
    suspend fun getPetBreeds(): List<String>?

    @Query("SELECT DISTINCT breeds_primary FROM pets WHERE type = :type")
    suspend fun getPetBreedsByType(type: String): List<String>?

    @Query("SELECT DISTINCT colors_primary FROM pets")
    suspend fun getPetColors(): List<String>?

    @Query("SELECT * FROM pets WHERE gender = :gender")
    suspend fun getPetsByGender(gender: String): List<Pet>?

    @Query("SELECT COUNT(*) FROM pets")
    suspend fun getDBsize(): Int

    @Delete
    suspend fun deletePet(pet: Pet)
}