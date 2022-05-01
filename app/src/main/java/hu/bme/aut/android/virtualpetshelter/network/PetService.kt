package hu.bme.aut.android.virtualpetshelter.network

import hu.bme.aut.android.virtualpetshelter.model.Pet
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PetService {

    @GET("pets")
    suspend fun getPetList(): Call<ResponseBody>

    @GET("pet/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Call<ResponseBody>

    @POST("pet/new")
    suspend fun addPet(@Body pet: Pet)

    @PUT("pet/update")
    suspend fun updatePet(@Body pet: Pet)

    @DELETE("pet/delete/{id}")
    suspend fun deletePet(@Path("id") id: Int)
}