package hu.bme.aut.android.virtualpetshelter.network

import hu.bme.aut.android.virtualpetshelter.model.Pet
import hu.bme.aut.android.virtualpetshelter.model.PetListResponse
import hu.bme.aut.android.virtualpetshelter.model.Token
import retrofit2.http.*

interface PetService {

    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String?,
        @Field("client_secret") clientSecret: String?): Token

    @GET("animals")
    suspend fun getPetList(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): PetListResponse

    @GET("animals/{id}")
    suspend fun getPet(@Path("id") id: Int): Pet

    // Not in actual public API

    @POST("pet/new")
    suspend fun addPet(@Body pet: Pet)

    @PUT("pet/update")
    suspend fun updatePet(@Body pet: Pet)

    @DELETE("pet/delete/{id}")
    suspend fun deletePet(@Path("id") id: Int)
}