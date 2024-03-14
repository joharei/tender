import domain.DomainModule
import network.NetworkModule
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules
import kotlin.test.Test

class CheckModulesTest : KoinTest {
	@Test
	fun verifyKoinApp() {
		checkKoinModules(
			listOf(
				NetworkModule().module,
				DomainModule().module,
			)
		)
	}
}