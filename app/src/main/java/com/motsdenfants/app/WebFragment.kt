package com.motsdenfants.app

import android.webkit.PermissionRequest
import dev.hotwire.core.turbo.session.Session
import dev.hotwire.core.turbo.webview.HotwireWebChromeClient
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

@HotwireDestinationDeepLink(uri = "hotwire://fragment/web")
open class WebFragment : HotwireWebFragment() {
    override fun createWebChromeClient(): HotwireWebChromeClient {
        return MicrophoneChromeClient(navigator.session)
    }
}

class MicrophoneChromeClient(session: Session) : HotwireWebChromeClient(session) {
    override fun onPermissionRequest(request: PermissionRequest) {
        if (request.resources.contains(PermissionRequest.RESOURCE_AUDIO_CAPTURE)) {
            request.grant(request.resources)
        } else {
            super.onPermissionRequest(request)
        }
    }
}