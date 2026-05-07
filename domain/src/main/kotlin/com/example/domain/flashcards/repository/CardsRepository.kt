package com.example.domain.flashcards.repository

import com.example.domain.flashcards.model.AnswerResult
import com.example.domain.flashcards.model.CardItem

interface CardsRepository {

    suspend fun get(): Result<CardItem>

    suspend fun answer(alternative: CardItem.Alternative, cardId: String): Result<AnswerResult>
}