package hu.bme.aut.android.virtualpetshelter.ui.main

import hu.bme.aut.android.virtualpetshelter.model.Pet
import hu.bme.aut.android.virtualpetshelter.network.PetService
import hu.bme.aut.android.virtualpetshelter.persistence.PetDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val petService: PetService,
    private val petDao: PetDao
    ) {

    suspend fun fetchPets() {
        var petList = petDao.getPetList()
        if (petList.isEmpty()) {
            var page = 1
            // Retrieve at least 250 pets that have picture attached
            while (petDao.getDBsize() < 250) {
                petList = petService.getPetList(100, page).petList
                petDao.insertAll(petList)
                petDao.deletePetsWithoutPicture()
                page++
            }
        }
    }

    suspend fun getAllPets() : List<Pet> = petDao.getPetList()

    suspend fun getPetListWithPhotos() : List<Pet>? = petDao.getPetListWithPhotos()

    suspend fun getPetList(): List<Pet> = petDao.getPetList()

    suspend fun getPet(id: Int): Pet? = petDao.getPet(id)

    suspend fun getPetsByNameSearch(nameSearchString: String): List<Pet>? = petDao.getPetsByNameSearch(nameSearchString)

    suspend fun getPetsByTypeSearch(typeSearchString: String): List<Pet>? = petDao.getPetsByTypeSearch(typeSearchString)

    suspend fun getPetsByBreedSearch(breedSearchString: String): List<Pet>? = petDao.getPetsByBreedSearch(breedSearchString)

    suspend fun getPetsByColor(color: String): List<Pet>? = petDao.getPetsByColor(color)

    suspend fun getPetsByType(type: String): List<Pet>? = petDao.getPetsByType(type)

    suspend fun getPetsByBreed(breed: String): List<Pet>? = petDao.getPetsByBreed(breed)

    suspend fun getPetsByTypeAndBreed(type: String, breed: String): List<Pet>? = petDao.getPetsByTypeAndBreed(type, breed)

    suspend fun getPetsByTypeAndGender(type: String, gender: String): List<Pet>? = petDao.getPetsByTypeAndGender(type, gender)

    suspend fun getPetsByBreedAndGender(breed: String, gender: String): List<Pet>? = petDao.getPetsByBreedAndGender(breed, gender)

    suspend fun getPetsByTypeAndBreedAndGender(type: String, breed: String, gender: String): List<Pet>? =
        petDao.getPetsByTypeAndBreedAndGender(type, breed, gender)

    suspend fun getPetTypes(): List<String>? = petDao.getPetTypes()

    suspend fun getPetBreeds(): List<String>? = petDao.getPetBreeds()

    suspend fun getPetBreedsByType(type: String): List<String>? = petDao.getPetBreedsByType(type)

    suspend fun getPetColors(): List<String>? = petDao.getPetColors()

    suspend fun getPetsByGender(gender: String): List<Pet>? = petDao.getPetsByGender(gender)
}