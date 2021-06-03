package com.example.melichallenge.di

import android.app.Application
import android.content.Context
import com.example.melichallenge.BuildConfig
import com.example.melichallenge.data.remote.ItemRemoteDataSource
import com.example.melichallenge.data.repository.ItemRepository
import com.example.melichallenge.data.remote.ItemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideItemService(retrofit: Retrofit): ItemService =
        retrofit.create(ItemService::class.java)

    @Singleton
    @Provides
    fun provideItemRemoteDataSource(itemService: ItemService) =
        ItemRemoteDataSource(itemService)

    @Singleton
    @Provides
    fun provideRepository(itemRemoteDataSource: ItemRemoteDataSource) =
        ItemRepository(itemRemoteDataSource)
}