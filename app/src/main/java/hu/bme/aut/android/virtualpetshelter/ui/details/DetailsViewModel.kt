package hu.bme.aut.android.virtualpetshelter.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.virtualpetshelter.model.Pet
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
                FirebaseCrashlytics.getInstance().log("Getting pet by ID failed")
                FirebaseCrashlytics.getInstance().recordException(e)
                e.printStackTrace()
            }
        }
    }
}