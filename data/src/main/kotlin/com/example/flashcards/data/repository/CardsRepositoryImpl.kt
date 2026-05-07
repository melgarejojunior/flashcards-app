package com.example.flashcards.data.repository

import com.example.domain.flashcards.model.AnswerResult
import com.example.domain.flashcards.model.CardItem
import com.example.domain.flashcards.model.ResultsScore
import com.example.domain.flashcards.repository.CardsRepository
import com.example.flashcards.data.remote.ApiClient
import com.example.flashcards.data.remote.model.AnswerOptionResponse
import com.example.flashcards.data.remote.model.ResultsResponse
import com.example.flashcards.data.remote.model.SubmitAnswerRequest
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(private val client: ApiClient) : CardsRepository {
    override suspend fun get(): Result<CardItem> {
        return runCatching {
            val response = client.getCard()

            CardItem(
                id = response.cardId,
                description = response.question,
                alternatives = toAlternatives(response.options)
            )
        }
    }

    override suspend fun answer(
        alternative: CardItem.Alternative,
        cardId: String
    ): Result<AnswerResult> {
        return runCatching {
            val response = client.answer(SubmitAnswerRequest(alternative.label), cardId)

            AnswerResult(
                cardId = response.cardId,
                correct = response.correct,
                message = response.message,
                correctLabel = response.correctLabel,
                correctAnswer = response.correctAnswer,
                results = toResultsScore(response.results),
            )
        }
    }

    private fun toResultsScore(results: ResultsResponse): ResultsScore {
        return ResultsScore(
            correct = results.correct,
            incorrect = results.incorrect,
            answered = results.answered
        )
    }

    private fun toAlternatives(options: List<AnswerOptionResponse>): List<CardItem.Alternative> {
        return options.map { option ->
            CardItem.Alternative(label = option.label, text = option.text)
        }
    }

}