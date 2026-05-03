package network

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal fun httpClient() = HttpClient {
	install(ContentNegotiation) {
		json(Json {
			ignoreUnknownKeys = true
		})
	}
	install(Logging) {
		logger = object : Logger {
			override fun log(message: String) {
				Napier.v(tag = "HTTP Client", message = message)
			}
		}
		level = LogLevel.INFO
	}
}.also { Napier.base(DebugAntilog()) }