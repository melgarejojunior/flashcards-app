package com.example.flashcards.data.remote.di

import com.example.flashcards.data.remote.ApiClient
import com.example.flashcards.data.remote.HttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient()

    @Provides
    @Singleton
    fun provideApiClient(client: io.ktor.client.HttpClient) = ApiClient(client)
}