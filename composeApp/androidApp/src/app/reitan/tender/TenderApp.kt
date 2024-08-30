package app.reitan.tender

import android.app.Application
import org.koin.android.ext.koin.androidContext
import shared.initKoin

class TenderApp : Application() {
	override fun onCreate() {
		super.onCreate()

		initKoin {
			androidContext(this@TenderApp)
		}
	}
}