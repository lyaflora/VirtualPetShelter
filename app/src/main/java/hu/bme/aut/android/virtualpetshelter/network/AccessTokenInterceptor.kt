package hu.bme.aut.android.virtualpetshelter.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.crashlytics.FirebaseCrashlytics
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Singleton


@Singleton
class AccessTokenInterceptor (private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        checkConnectivity()
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

    private fun checkConnectivity() {
        try {
            if (!isConnected())
                throw NoConnectivityException("Failed to connect to internet")
        } catch (e: Exception) {
            FirebaseCrashlytics.getInstance().log("Connecting to internet failed")
            FirebaseCrashlytics.getInstance().recordException(e)
            e.printStackTrace()
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        val connected = netInfo != null && netInfo.isConnected
        if (!connected) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(
                    context,
                    "Could not connect to internet. Please check your connection.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return connected
    }
}