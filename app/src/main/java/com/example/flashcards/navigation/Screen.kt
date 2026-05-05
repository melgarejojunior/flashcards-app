package com.example.flashcards.navigation

sealed class Screen(val route: String) {
    object Card: Screen("card-screen")
}