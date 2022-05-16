package hu.bme.aut.android.virtualpetshelter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.android.virtualpetshelter.model.Converters
import hu.bme.aut.android.virtualpetshelter.model.Pet

@Database(entities = [Pet::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PetDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao
}