package api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import model.ImageDataClass
import singleton.ApiHeader


class ApiClass {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun greeting(url:String): ImageDataClass {
        val response = client.get(url)
        {
            headers {
                append(HttpHeaders.Authorization, ApiHeader.auth)
            }
        }

        return response.body()
    }

    suspend fun searchImage(url:String): ImageDataClass {
        val response = client.get(url)
        {
            headers {
                append(HttpHeaders.Authorization, ApiHeader.auth)
            }
        }

        return response.body()
    }
}