package com.example.flashcards.screens.card

import com.example.domain.flashcards.model.CardItem

sealed class CardUIState() {
    data object Loading : CardUIState()
    data class Error(val errorMessage: String) : CardUIState()
    data class Success(val cardItem: CardItem) : CardUIState()
}
