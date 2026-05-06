package com.example.flashcards.screens.card

import androidx.lifecycle.ViewModel
import com.example.domain.flashcards.CardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<CardUIState>(
        CardUIState.Success(
            cardItem = CardItem(
                title = "Title", description = "Description", alternatives = listOf(
                    CardItem.Alternative(id = 0, text = "Something"),
                    CardItem.Alternative(id = 1, text = "Another thing")
                )
            )
        )
    )
    val state: StateFlow<CardUIState> = _state.asStateFlow()


    fun onAlternativeChosen(alternative: CardItem.Alternative) {

    }
}