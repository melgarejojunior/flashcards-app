package com.example.domain.flashcards.usecase

import com.example.domain.flashcards.model.CardItem
import com.example.domain.flashcards.repository.CardsRepository
import javax.inject.Inject


class GetCard @Inject constructor(private val repository: CardsRepository) {
    suspend operator fun invoke(): Result<CardItem> {
        return repository.get()
    }
}