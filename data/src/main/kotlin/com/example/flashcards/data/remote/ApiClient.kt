package com.example.flashcards.data.remote

import com.example.domain.flashcards.model.AnswerResult
import com.example.flashcards.data.remote.model.AnswerResultResponse
import com.example.flashcards.data.remote.model.CardPromptResponse
import com.example.flashcards.data.remote.model.SubmitAnswerRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import javax.inject.Inject

class ApiClient @Inject constructor(private val client: HttpClient) {

    suspend fun getCard(): CardPromptResponse {
        return client.get("/cards/next").body()
    }

    suspend fun answer(request: SubmitAnswerRequest, cardId: String): AnswerResultResponse {
        return client.post("/cards/$cardId/answer").body()
    }
}