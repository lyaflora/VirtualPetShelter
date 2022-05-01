package hu.bme.aut.android.virtualpetshelter.ui.main

import hu.bme.aut.android.virtualpetshelter.network.PetService
import hu.bme.aut.android.virtualpetshelter.persistence.PetDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val petService: PetService,
    private val petDao: PetDao
    )