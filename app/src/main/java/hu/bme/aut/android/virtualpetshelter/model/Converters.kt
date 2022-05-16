package hu.bme.aut.android.virtualpetshelter.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun photoListToJsonString(value: List<Photos>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToPhotoList(value: String) = Gson().fromJson(value, Array<Photos>::class.java).toList()

    @TypeConverter
    fun stringListToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun petListToJsonString(value: List<Pet>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToPetList(value: String) = Gson().fromJson(value, Array<Pet>::class.java).toList()

    @TypeConverter
    fun videoListToJsonString(value: List<Videos>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToVideoList(value: String) = Gson().fromJson(value, Array<Videos>::class.java).toList()
}