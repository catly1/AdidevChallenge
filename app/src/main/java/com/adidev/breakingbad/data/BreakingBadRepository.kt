package com.adidev.breakingbad.data

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class BreakingBadRepository {

    private val client = HttpClient(Android){
        install(JsonFeature){
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json{
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchCharacters(): List<Character> {
        return client.get("https://breakingbadapi.com/api/characters")
    }
}