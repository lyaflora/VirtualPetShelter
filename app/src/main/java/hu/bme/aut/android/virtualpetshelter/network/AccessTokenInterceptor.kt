package hu.bme.aut.android.virtualpetshelter.network

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AccessTokenInterceptor (private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("NetworkModule", "Context injected into interceptor")
        var request: Request = chain.request()
        val preferences = context.getSharedPreferences("Authorization", Context.MODE_PRIVATE)
        request = request.newBuilder()
            .addHeader(
                "AccessToken",
                preferences.getString("accessToken", "null")!!
            )
            .build()
        return chain.proceed(request)
    }
}