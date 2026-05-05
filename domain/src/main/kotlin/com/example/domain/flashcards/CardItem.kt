package com.example.domain.flashcards

data class CardItem(val title: String, val description: String, val alternatives: List<Alternative>) {
    data class Alternative(val id: Int, val text: String)
}
