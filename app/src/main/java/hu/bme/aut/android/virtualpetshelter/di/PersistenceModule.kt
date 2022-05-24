package hu.bme.aut.android.virtualpetshelter.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.virtualpetshelter.persistence.PetDao
import hu.bme.aut.android.virtualpetshelter.persistence.PetDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun providePetDatabase(@ApplicationContext context: Context): PetDatabase {
        return Room
            .databaseBuilder(
                context,
                PetDatabase::class.java,
                "pets.db"
            )
//            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePetDao(petDatabase: PetDatabase): PetDao {
        return petDatabase.getPetDao()
    }
}