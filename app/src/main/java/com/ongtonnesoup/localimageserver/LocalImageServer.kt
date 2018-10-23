package com.ongtonnesoup.localimageserver

import com.github.ajalt.timberkt.Timber
import fi.iki.elonen.NanoHTTPD

private const val LOCAL_IMAGE_REQUEST = "/image/"

class LocalImageServer(
    configuration: LocalImageServer.Configuration,
    private val repository: ImageRepository
) : NanoHTTPD(configuration.hostname, configuration.port) {

    override fun serve(request: IHTTPSession): Response {
        val isLocalImageRequest = request.uri == LOCAL_IMAGE_REQUEST
        return if (isLocalImageRequest) {
            loadLocalImage(getOriginalSrcFrom(request))
        } else {
            throw IllegalArgumentException()
        }
    }

    private fun getOriginalSrcFrom(session: IHTTPSession): String {
        val parameters = session.parameters["src"]

        require(parameters != null && parameters.size == 1) { "Malformed local image URL" }

        return parameters[0].also {
            Timber.d { "Request for local image: $it" }
        }
    }

    private fun loadLocalImage(originalSrc: String): Response {
        Thread.sleep(2500) // Simulate delays and how it doesn't blocked initial load

        val localImage = repository.loadImage(originalSrc)

        return newChunkedResponse(
            Response.Status.OK,
            "image/jpeg",
            localImage.stream
        )
    }

    data class Configuration(
        val hostname: String = "localhost",
        val port: Int = 8080
    )
}