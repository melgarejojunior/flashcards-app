package com.example.flashcards.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SubmitAnswerRequest(
    val selectedLabel: String,
)