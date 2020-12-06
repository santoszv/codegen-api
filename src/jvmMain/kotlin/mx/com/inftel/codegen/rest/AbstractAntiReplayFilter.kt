package mx.com.inftel.codegen.rest

import mx.com.inftel.codegen.exceptions.AntiReplayException
import java.util.*
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.core.Response

abstract class AbstractAntiReplayFilter : ContainerRequestFilter, ContainerResponseFilter {

    protected abstract fun consumeToken(token: UUID): Boolean

    protected abstract fun produceToken(): UUID

    override fun filter(requestContext: ContainerRequestContext) {
        val token: UUID = try {
            val header = requestContext.getHeaderString(ANTI_REPLAY_TOKEN_HEADER)
            if (header.isNullOrBlank()) {
                throw AntiReplayException()
            }
            UUID.fromString(header)
        } catch (ex: IllegalArgumentException) {
            throw AntiReplayException()
        }
        if (token.leastSignificantBits == 0L && token.mostSignificantBits == 0L) {
            if ("HEAD".contentEquals(requestContext.method)) {
                requestContext.setProperty(GENERATE_ANTI_REPLAY_TOKEN_PROPERTY, true)
                requestContext.abortWith(Response.noContent().build())
            } else {
                throw AntiReplayException()
            }
        } else {
            if (consumeToken(token)) {
                requestContext.setProperty(GENERATE_ANTI_REPLAY_TOKEN_PROPERTY, true)
            } else {
                throw AntiReplayException()
            }
        }
    }

    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        if (requestContext.getProperty(GENERATE_ANTI_REPLAY_TOKEN_PROPERTY) == true) {
            responseContext.headers.putSingle(ANTI_REPLAY_TOKEN_HEADER, produceToken())
        }
    }
}