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
    val errorMessage = MutableLiveData<String>()
    private var job: Job? = null

    fun getPetList() {
        Log.d("LoadPets", "ViewModel trying to call Repository to load pets...")
        viewModelScope.launch {
            try {
                val pets = mainRepository.loadPets()
                petList.postValue(pets)
                for (pet in pets) {
                    Log.d("LoadPets", pet.name!!)
                }
            }
            catch ( e : Exception )
            {
                e.printStackTrace()
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}