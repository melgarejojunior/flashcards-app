package com.example.flashcards.data.remote.model

import kotlinx.serialization.Serializable
@Serializable
data class CardPromptResponse(
    val cardId: String,
    val question: String,
    val options: List<AnswerOptionResponse>,
)
