package com.devj.todoproducts.core.data.network.di

import com.devj.todoproducts.core.data.network.api.ProductApi
import com.devj.todoproducts.core.data.network.api.ProductApiConstant
import com.devj.todoproducts.core.data.network.api.ProductApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.isSuccess
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(OkHttp) {

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = ProductApiConstant.BASE_URL
                    path(ProductApiConstant.API)
                    path(ProductApiConstant.API_VERSION)
                }
                headers.append("Content-Type", "application/json")
            }
            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(HttpRequestRetry) {
                maxRetries = 5
                retryIf { request, response ->
                    !response.status.isSuccess()
                }
                retryOnExceptionIf { request, cause ->
                    false
                }
                delayMillis { retry ->
                    retry * 3000L
                }
            }
            install(Logging)

        }
    }

    single<ProductApi> {
        ProductApiImpl(get())
    }

}
