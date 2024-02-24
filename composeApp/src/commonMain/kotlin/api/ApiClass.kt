package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import model.ImageDataClass


class ApiClass {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun greeting(): ImageDataClass {
        val response = client.get("https://api.pexels.com/v1/curated?per_page=15")
        {
            headers {
                append(HttpHeaders.Authorization, "Your API Key")
            }
        }

        return response.body()
    }
}