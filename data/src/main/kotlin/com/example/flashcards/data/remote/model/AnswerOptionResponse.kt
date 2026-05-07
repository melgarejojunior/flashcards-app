package com.example.flashcards.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AnswerOptionResponse(
    val label: String,
    val text: String,
)