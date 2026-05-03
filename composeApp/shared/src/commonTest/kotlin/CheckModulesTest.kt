import io.ktor.client.*
import io.ktor.client.engine.*
import org.koin.test.KoinTest
import org.koin.test.verify.verify
import shared.appModule
import kotlin.test.Test

class CheckModulesTest : KoinTest {
	@Test
	fun verifyKoinAppModule() {
		appModule.verify(
			extraTypes = listOf(HttpClientEngine::class, HttpClientConfig::class)
		)
	}
}