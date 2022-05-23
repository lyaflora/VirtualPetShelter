package hu.bme.aut.android.virtualpetshelter.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.virtualpetshelter.model.Pet
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val petList = MutableLiveData<List<Pet>>()
    val loading = MutableLiveData<Boolean>()
    val petTypes = MutableLiveData<List<String>>()
    val selectedType = MutableLiveData<String>("")
    val petBreeds = MutableLiveData<List<String>>()
    val selectedBreed = MutableLiveData<String>("")
    val selectedGender = MutableLiveData<String>("")


    fun loadPetListWithPhotos() {
        viewModelScope.launch {
            try {
                loading.postValue(true)
                mainRepository.fetchPets()
                val pets = mainRepository.getPetListWithPhotos()
                petList.postValue(pets!!)
                val types = mainRepository.getPetTypes()
                petTypes.postValue(types!!)
                val breeds = mainRepository.getPetBreeds()
                petBreeds.postValue(breeds!!)
                selectedType.postValue("")
                selectedBreed.postValue("")
                selectedGender.postValue("")
                loading.postValue(false)
            }
            catch ( e : Exception )
            {
                e.printStackTrace()
            }
        }
    }

    fun updateSelectedPetType(type: String) {
        val newType = if (type == "Select pet type!") "" else type
        selectedType.value = newType
        updateBreeds(newType)
        filterPets()
    }

    fun updateSelectedPetBreed(breed: String) {
        val newBreed = if (breed == "Select pet breed!") "" else breed
        selectedBreed.value = newBreed
        filterPets()
    }

    fun updateSelectedPetGender(gender: String) {
        val newGender = if (gender == "Select pet gender!") "" else gender
        selectedGender.value = newGender
        filterPets()
    }

    private fun filterPets() {
//        Log.d("Search", "Bred: ${selectedBreed.value!!}")
        viewModelScope.launch {
            try {
                val filteredPets =
                    if (selectedBreed.value == "" && selectedType.value == "" && selectedGender.value == "")
                        mainRepository.getAllPets()
                    else if (selectedBreed.value == "" && selectedType.value == "")
                        mainRepository.getPetsByGender(selectedGender.value!!)
                    else if (selectedBreed.value == "" && selectedGender.value == "")
                        mainRepository.getPetsByType(selectedType.value!!)
                    else if (selectedType.value == "" && selectedGender.value == "")
                        mainRepository.getPetsByBreed(selectedBreed.value!!)
                    else if (selectedBreed.value == "")
                        mainRepository.getPetsByTypeAndGender(selectedType.value!!, selectedGender.value!!)
                    else if (selectedType.value == "")
                        mainRepository.getPetsByBreedAndGender(selectedBreed.value!!, selectedGender.value!!)
                    else if (selectedGender.value == "")
                        mainRepository.getPetsByTypeAndBreed(selectedType.value!!, selectedBreed.value!!)
                    else
                        mainRepository.getPetsByTypeAndBreedAndGender(selectedType.value!!, selectedBreed.value!!, selectedGender.value!!)
                petList.postValue(filteredPets!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateBreeds(type: String) {
        viewModelScope.launch {
            try {
                val breeds =
                    if (type == "")
                        mainRepository.getPetBreeds()
                    else
                        mainRepository.getPetBreedsByType(type)
                petBreeds.postValue(breeds!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}