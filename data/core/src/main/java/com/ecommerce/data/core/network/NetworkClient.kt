package com.ecommerce.data.core.network

import com.ecommerce.build_config.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import timber.log.Timber
import java.util.concurrent.TimeUnit

object NetworkClient {
    private const val HTTP_TAG = "HttpLog"
    fun get() = HttpClient(OkHttp) {
        expectSuccess = true
        engine {
            config {
                connectTimeout(1, TimeUnit.MINUTES)
                readTimeout(1, TimeUnit.MINUTES)
            }
        }
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            header("Accept", "application/json")
            url {
                protocol = URLProtocol.HTTPS
                host = BuildConfig.API_BASE_URL
            }
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) = Timber.tag(HTTP_TAG).d(message)
            }
            level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
        }
    }
}
