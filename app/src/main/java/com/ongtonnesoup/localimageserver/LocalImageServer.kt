package com.ongtonnesoup.localimageserver

import com.github.ajalt.timberkt.Timber
import fi.iki.elonen.NanoHTTPD

class LocalImageServer(
    configuration: LocalImageServer.Configuration
) : NanoHTTPD(configuration.hostname, configuration.port) {

    override fun serve(session: IHTTPSession): Response {
        Timber.d { "JJO: $session" }
        return super.serve(session)
    }

    data class Configuration(
        val hostname: String = "localhost",
        val port: Int = 8080
    )
}