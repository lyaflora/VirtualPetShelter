package hu.bme.aut.android.virtualpetshelter.di

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.virtualpetshelter.BuildConfig
import hu.bme.aut.android.virtualpetshelter.network.AccessTokenAuthenticator
import hu.bme.aut.android.virtualpetshelter.network.PetService
import hu.bme.aut.android.virtualpetshelter.network.AccessTokenInterceptor
import hu.bme.aut.android.virtualpetshelter.network.AccessTokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.petfinder.com/v2/"

    @Provides
    @Singleton
    fun provideCredentials(): Pair<String?, String?> {
        Log.d("NetworkModule", "Providing credentials...")
        return Pair(BuildConfig.API_KEY, BuildConfig.SECRET)
    }

    @Provides
    @Singleton
    fun provideAccessTokenManager(@ApplicationContext context: Context, petServiceProvider: Provider<PetService>): AccessTokenManager {
        Log.d("NetworkModule", "Providing access token manager...")
        return AccessTokenManager(context, petServiceProvider)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        Log.d("NetworkModule", "Providing okhttp client...")
        return OkHttpClient.Builder()
            .authenticator(AccessTokenAuthenticator(context))
            .addInterceptor(AccessTokenInterceptor(context))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        Log.d("NetworkModule", "Providing retrofit...")
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providePetService(retrofit: Retrofit): PetService {
        Log.d("NetworkModule", "Providing pet service...")
        return retrofit.create(PetService::class.java)
    }
}