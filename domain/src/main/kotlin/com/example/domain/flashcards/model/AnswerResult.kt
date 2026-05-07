package com.example.domain.flashcards.model

data class AnswerResult(
    val cardId: String,
    val correct: Boolean,
    val message: String,
    val correctLabel: String,
    val correctAnswer: String,
    val results: ResultsScore,
)