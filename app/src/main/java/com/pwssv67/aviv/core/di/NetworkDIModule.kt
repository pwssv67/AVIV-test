package com.pwssv67.aviv.core.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.*

val NetworkDIModule = module {
    single<HttpClient> { HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    Log.d("Ktor Logger", message)
                }
            }
            level = LogLevel.ALL
        }

        defaultRequest {
            url {
                host = "gsl-apps-technical-test.dignp.com"
                protocol = URLProtocol.HTTPS

                port = 443
            }
            this.headers {
                this.clear()
                //this.append("App-Author", "Egor Smolianinov")
            }
        }
    } }
}