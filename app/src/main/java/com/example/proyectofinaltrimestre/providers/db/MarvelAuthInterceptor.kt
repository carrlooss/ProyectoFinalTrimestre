package com.example.proyectofinaltrimestre.providers.db

import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

class MarvelAuthInterceptor(
    private val publicKey: String,
    private val privateKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url

        val ts = System.currentTimeMillis().toString()
        val hash = generateMd5("$ts$privateKey$publicKey")

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .build()

        val newRequest = original.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    private fun generateMd5(input: String): String {
        val bytes = MessageDigest.getInstance("MD5").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
