package hu.bme.aut.android.virtualpetshelter.ui.details

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.virtualpetshelter.persistence.PetDao
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val petDao: PetDao
    ) {

    suspend fun getPet(id: Int) = petDao.getPet(id)
}