package com.example.flashcards.data.remote.di

import com.example.domain.flashcards.repository.CardsRepository
import com.example.flashcards.data.repository.CardsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindCardsRepository(repository: CardsRepositoryImpl): CardsRepository
}