package com.example.flashcards.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultsResponse(
    val correct: Int,
    val incorrect: Int,
    val answered: Int,
)