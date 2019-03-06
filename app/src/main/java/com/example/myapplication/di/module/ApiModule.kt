package com.example.myapplication.di.module

import android.content.Context
import com.example.myapplication.networking.ApiService
import com.example.myapplication.utils.BASE_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(context.cacheDir, cacheSize.toLong())
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val client = OkHttpClient.Builder()
        client.addInterceptor(httpLoggingInterceptor)
        client.addNetworkInterceptor(StethoInterceptor())
        return client.build()
    }

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(okHttpClient)
                .build().create(ApiService::class.java)
    }
}