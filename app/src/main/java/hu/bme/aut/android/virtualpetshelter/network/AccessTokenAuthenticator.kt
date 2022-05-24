package hu.bme.aut.android.virtualpetshelter.network

import android.content.Context
import android.util.Log
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Singleton

@Singleton
class AccessTokenAuthenticator (private val context: Context) : Authenticator {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface AccessTokenAuthenticatorEntryPoint {
        fun getAccessTokenManager(): AccessTokenManager
    }

    private val accessTokenManager : AccessTokenManager = EntryPoints.get(context, AccessTokenAuthenticatorEntryPoint::class.java).getAccessTokenManager()

    override fun authenticate(route: Route?, response: Response): Request {
        Log.d("NetworkModule", "Access token manager injected into authenticator")
        val accessToken: String? = accessTokenManager.getAccessToken()
        synchronized(this) {
            val newAccessToken: String? = accessTokenManager.getAccessToken()
            if (accessToken != newAccessToken && newAccessToken != null) {
                Log.d("TokenAuth", "Get token")
                return newRequestWithAccessToken(response.request(), newAccessToken)
            }

            val updatedAccessToken: String? = runBlocking {
                Log.d("TokenAuth", "Refresh token")
                accessTokenManager.refreshAccessToken()
            }
            return newRequestWithAccessToken(response.request(), updatedAccessToken)
        }
    }

    private fun newRequestWithAccessToken(request: Request, accessToken: String?): Request {
        return request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
    }
}