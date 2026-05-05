package com.example.flashcards.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcards.screens.card.CardScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Card.route) {
        composable(route = Screen.Card.route) {
            CardScreen(navController = navController)
        }
    }
}