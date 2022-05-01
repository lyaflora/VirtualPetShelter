package hu.bme.aut.android.virtualpetshelter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.android.virtualpetshelter.model.Pet

@Database(entities = [Pet::class], version = 2, exportSchema = false)
abstract class PetDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
}