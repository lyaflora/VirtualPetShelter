package hu.bme.aut.android.virtualpetshelter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.virtualpetshelter.network.PetService
import hu.bme.aut.android.virtualpetshelter.persistence.PetDao
import hu.bme.aut.android.virtualpetshelter.ui.details.DetailsRepository
import hu.bme.aut.android.virtualpetshelter.ui.main.MainRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        petService: PetService,
        petDao: PetDao
    ): MainRepository {
        return MainRepository(petService, petDao)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailsRepository(
        petDao: PetDao
    ): DetailsRepository {
        return DetailsRepository(petDao)
    }
}