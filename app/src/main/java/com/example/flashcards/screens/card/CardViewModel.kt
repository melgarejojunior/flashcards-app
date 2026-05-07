package com.example.flashcards.screens.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.flashcards.model.CardItem
import com.example.domain.flashcards.usecase.GetCard
import com.example.domain.flashcards.usecase.SubmitAnswer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCard: GetCard,
    private val submitAnswer: SubmitAnswer,
) : ViewModel() {
    private val _state = MutableStateFlow<CardUIState>(
        CardUIState.Success(
            cardItem = CardItem(
                id = "Title", description = "Description", alternatives = listOf(
                    CardItem.Alternative(label = "A", text = "Something"),
                    CardItem.Alternative(label = "B", text = "Another thing")
                )
            )
        )
    )
    val state: StateFlow<CardUIState> = _state.asStateFlow()

    init {
        _state.update { CardUIState.Loading }
        viewModelScope.launch {
            getCard().fold(
                onSuccess = { card ->
                    _state.update { CardUIState.Success(cardItem = card) }
                },
                onFailure = { error -> CardUIState.Error(errorMessage = error.stackTraceToString()) },
            )
        }
    }


    fun onAlternativeChosen(alternative: CardItem.Alternative) {

    }
}