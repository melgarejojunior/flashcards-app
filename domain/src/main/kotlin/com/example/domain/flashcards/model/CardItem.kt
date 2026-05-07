package com.example.domain.flashcards.model

data class CardItem(val id: String, val description: String, val alternatives: List<Alternative>) {
    data class Alternative(val label: String, val text: String) {
        override fun toString(): String {
            return "$label - $text"
        }
    }
}