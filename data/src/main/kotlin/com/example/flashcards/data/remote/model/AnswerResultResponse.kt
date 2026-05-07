package com.example.flashcards.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AnswerResultResponse(
    val cardId: String,
    val correct: Boolean,
    val message: String,
    val correctLabel: String,
    val correctAnswer: String,
    val results: ResultsResponse,
)
