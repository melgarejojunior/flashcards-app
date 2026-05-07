package com.example.domain.flashcards.usecase

import com.example.domain.flashcards.model.AnswerResult
import com.example.domain.flashcards.model.CardItem
import com.example.domain.flashcards.repository.CardsRepository
import javax.inject.Inject

class SubmitAnswer @Inject constructor(private val repository: CardsRepository) {
    suspend fun invoke(cardId: String, alternative: CardItem.Alternative): Result<AnswerResult> {
        return repository.answer(alternative = alternative, cardId = cardId)
    }
}