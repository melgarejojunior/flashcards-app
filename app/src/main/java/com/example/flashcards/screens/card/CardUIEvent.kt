package com.example.flashcards.screens.card

import com.example.domain.flashcards.model.AnswerResult

sealed interface CardUIEvent {
    data class AnswerReceived(val answerResult: AnswerResult) : CardUIEvent
}