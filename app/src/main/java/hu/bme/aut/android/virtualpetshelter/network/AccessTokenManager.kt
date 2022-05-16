package hu.bme.aut.android.virtualpetshelter.network

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
class AccessTokenManager (private val context: Context, private val petServiceProvider: Provider<PetService>) {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface AccessTokenManagerEntryPoint {
        fun getCredentials(): Pair<String?, String?>
    }

    private val grantType: String = "client_credentials"

    private val credentials : Pair<String?, String?> = EntryPoints.get(context, AccessTokenManagerEntryPoint::class.java).getCredentials()

    fun getAccessToken(): String? {
        Log.d("NetworkModule", "Context injected into access token manager")
        val preferences = context.getSharedPreferences("Authorization", Context.MODE_PRIVATE)
        Log.d("TokenAuth", "Access token retrieved from sharedpreferences: ${preferences.getString("accessToken", null)}")
        return preferences.getString("accessToken", null)
    }

    suspend fun refreshAccessToken(): String? {
        val token = withContext(Dispatchers.IO) {
            Log.d("PetService", "Getting token...")
            petServiceProvider.get().getAccessToken(grantType, credentials.first, credentials.second).accessToken
        }
        Log.d("RefreshToken", "Retrieved token: $token")
        val preferences = context.getSharedPreferences("Authorization", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("accessToken", token)
        editor.apply()
        return token
    }

}