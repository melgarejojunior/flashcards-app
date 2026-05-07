package com.example.flashcards.screens.card

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.flashcards.model.CardItem
import com.example.domain.flashcards.usecase.GetCard
import com.example.domain.flashcards.usecase.SubmitAnswer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getCard: GetCard,
    private val submitAnswer: SubmitAnswer,
) : ViewModel() {
    private val _state = MutableStateFlow<CardUIState>(CardUIState.Loading)
    val state: StateFlow<CardUIState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<CardUIEvent>()
    val event: SharedFlow<CardUIEvent> = _event.asSharedFlow()

    init {
       onNextClicked()
    }


    fun onAlternativeChosen(cardId: String, alternative: CardItem.Alternative) {
        viewModelScope.launch {
            submitAnswer(cardId, alternative).fold(
                onSuccess = { answerResult ->
                    Log.e("REQUEST_ERROR", answerResult.toString())
                    _event.emit(CardUIEvent.AnswerReceived(answerResult)) },
                onFailure = { error ->
                    Log.e("REQUEST_ERROR", error.stackTraceToString())
                    _state.update { CardUIState.Error(error.stackTraceToString()) }})
        }
    }

    fun onNextClicked() {
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
}