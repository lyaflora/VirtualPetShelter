package hu.bme.aut.android.virtualpetshelter.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.virtualpetshelter.model.Pet
import hu.bme.aut.android.virtualpetshelter.ui.main.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    val pet = MutableLiveData<Pet>()

    fun getById(id: Int) {
        viewModelScope.launch {
            try {
                val petById = detailsRepository.getPet(id)
                pet.postValue(petById!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}