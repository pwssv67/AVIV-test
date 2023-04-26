package com.pwssv67.aviv.utils

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

suspend inline fun <reified T> HttpResponse.toResult(): Result<T> {
    return if (status.isSuccess()) {
        Result.success(body())
    } else {
        Result.failure(Throwable(status.description))
    }
}