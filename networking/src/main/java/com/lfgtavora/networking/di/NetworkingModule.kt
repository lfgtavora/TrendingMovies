package com.lfgtavora.networking.di

import android.content.Context
import android.util.Log
import com.lfgtavora.networking.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.core.module.Module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.dsl.module

private const val TIME_OUT = 60_000

lateinit var networkingKoin: KoinApplication
    private set

var modules = listOf<Module>()

private fun networkingKoinInit(context: Context) = koinApplication {
    androidLogger(Level.ERROR)
    androidContext(context)
    modules = listOf(ktorConfigurationModule)
    koin.unloadModules(modules)
    modules(modules)
}

fun init(context: Context) {
    networkingKoin = networkingKoinInit(context)
}

val ktorConfigurationModule = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

                engine {
                    connectTimeout = TIME_OUT
                    socketTimeout = TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }

            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header(HttpHeaders.Authorization, "Bearer " + BuildConfig.TMDB_JWT_TOKEN)
            }
        }
    }
}

