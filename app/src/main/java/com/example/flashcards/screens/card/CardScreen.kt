package com.example.flashcards.screens.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.domain.flashcards.CardItem

@Composable
fun CardScreen(navController: NavController, viewModel: CardViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val uiState = state) {
        CardUIState.Loading -> Loading()
        is CardUIState.Error -> Error(uiState.errorMessage)
        is CardUIState.Success -> Content(uiState.cardItem, viewModel::onAlternativeChosen)
    }
}

@Composable
fun Loading() {
    CircularProgressIndicator()
}

@Composable
fun Error(errorMessage: String) {
    Text(errorMessage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(cardItem: CardItem, onAlternativeChosen: (CardItem.Alternative) -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Flashcard App") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray)
        )
    }) { innerPadding ->
        CardComposable(modifier = Modifier.padding(innerPadding), cardItem, onAlternativeChosen)
    }
}

@Composable
fun CardComposable(
    modifier: Modifier,
    card: CardItem,
    onAlternativeChosen: (CardItem.Alternative) -> Unit
) {
    Card(
        modifier = modifier.padding(all = 16.dp),
        elevation = CardDefaults.cardElevation(),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = card.title)
            Box(Modifier.height(8.dp))
            Text(modifier = Modifier.fillMaxWidth(), text = card.description)
            AlternativeListItem(card.alternatives, onAlternativeChosen)
        }
    }
}

@Composable
fun AlternativeListItem(alternatives: List<CardItem.Alternative>,  onAlternativeChosen: (CardItem.Alternative) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        items(items = alternatives) {
            Button(modifier = Modifier.fillMaxWidth(), onClick = { onAlternativeChosen(it)}) {
                Text(text = "${it.id} - ${it.text}")
            }
        }
    }
}

@Composable
fun AnswerComposable() {

}

@Preview
@Composable
fun Preview() {
    CardScreen(navController = NavController(LocalContext.current))
}