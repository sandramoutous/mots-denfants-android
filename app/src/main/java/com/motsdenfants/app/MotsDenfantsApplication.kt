package com.motsdenfants.app

import android.app.Application
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.defaultFragmentDestination
import dev.hotwire.navigation.config.registerBridgeComponents
import dev.hotwire.navigation.config.registerFragmentDestinations

class MotsDenfantsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Hotwire.defaultFragmentDestination = WebFragment::class
        Hotwire.config.webViewDebuggingEnabled = true
        Hotwire.registerFragmentDestinations(
            WebFragment::class
        )

        Hotwire.registerBridgeComponents(
            BridgeComponentFactory("permissions", ::PermissionsComponent)
        )
    }
}