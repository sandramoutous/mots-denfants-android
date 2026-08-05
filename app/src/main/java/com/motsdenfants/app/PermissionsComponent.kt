package com.motsdenfants.app

import android.util.Log
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination

interface MicrophonePermissionRequester {
    fun requestMicrophonePermission(callback: (Boolean) -> Unit)
}

class PermissionsComponent(
    name: String,
    private val delegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, delegate) {

    override fun onReceive(message: Message) {
        when (message.event) {
            "requestMicrophone" -> requestMicrophone()
            "microphoneDenied" -> { } // le web s'abonne juste à la réponse
            else -> Log.w("PermissionsComponent", "Unknown event: ${message.event}")
        }
    }

    private fun requestMicrophone() {
        val requester = delegate.destination.fragment.activity as? MicrophonePermissionRequester
        if (requester == null) {
            Log.w("PermissionsComponent", "Activity does not implement MicrophonePermissionRequester")
            return
        }

        requester.requestMicrophonePermission { granted ->
            if (granted) replyTo("requestMicrophone") else replyTo("microphoneDenied")
        }
    }
}