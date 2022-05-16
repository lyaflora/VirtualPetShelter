package hu.bme.aut.android.virtualpetshelter.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.virtualpetshelter.persistence.PetDao
import hu.bme.aut.android.virtualpetshelter.persistence.PetDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun providePetDatabase(application: Application): PetDatabase {
        return Room
            .databaseBuilder(
                application,
                PetDatabase::class.java,
                "pets.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePetDao(petDatabase: PetDatabase): PetDao {
        return petDatabase.petDao()
    }
}