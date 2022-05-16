package hu.bme.aut.android.virtualpetshelter.ui.main

import android.util.Log
import hu.bme.aut.android.virtualpetshelter.model.Pet
import hu.bme.aut.android.virtualpetshelter.network.PetService
import hu.bme.aut.android.virtualpetshelter.persistence.PetDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val petService: PetService,
    private val petDao: PetDao
    ) {

    suspend fun loadPets() : List<Pet> {
        Log.d("LoadPets", "Repository trying to call Service to load pets...")
        Log.d("PetService", "Loading pets...")
        return petService.getPetList().petList
    }

    suspend fun getPetList(): List<Pet> = petDao.getPetList()

    suspend fun getPet(id: Int): Pet? = petDao.getPet(id)

    suspend fun getPetsByNameSearch(nameSearchString: String): List<Pet>? = petDao.getPetsByNameSearch(nameSearchString)

    suspend fun getPetsByTypeSearch(typeSearchString: String): List<Pet>? = petDao.getPetsByTypeSearch(typeSearchString)

    suspend fun getPetsByBreedSearch(breedSearchString: String): List<Pet>? = petDao.getPetsByBreedSearch(breedSearchString)

    suspend fun getPetsByColor(color: String): List<Pet>? = petDao.getPetsByColor(color)

    suspend fun getPetsByType(type: String): List<Pet>? = petDao.getPetsByType(type)

    suspend fun getPetTypes(): List<String>? = petDao.getPetTypes()

    suspend fun getPetBreeds(): List<String>? = petDao.getPetBreeds()

    suspend fun getPetColors(): List<String>? = petDao.getPetColors()

    suspend fun getPetsByGender(gender: String): List<Pet>? = petDao.getPetsByGender(gender)
}