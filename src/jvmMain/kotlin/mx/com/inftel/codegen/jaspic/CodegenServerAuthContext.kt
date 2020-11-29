package mx.com.inftel.codegen.jaspic

import mx.com.inftel.codegen.rest.AUTHENTICATION_TOKEN_HEADER
import mx.com.inftel.codegen.servlet.AUTH_TOKEN_ATTRIBUTE
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger
import javax.security.auth.Subject
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.message.AuthStatus
import javax.security.auth.message.MessageInfo
import javax.security.auth.message.callback.CallerPrincipalCallback
import javax.security.auth.message.callback.GroupPrincipalCallback
import javax.security.auth.message.config.ServerAuthContext
import javax.servlet.http.HttpServletRequest

class CodegenServerAuthContext(private val validateAuthToken: (UUID) -> CodegenPrincipal?, private val handler: CallbackHandler) : ServerAuthContext {

    override fun validateRequest(messageInfo: MessageInfo, clientSubject: Subject?, serviceSubject: Subject?): AuthStatus {
        return try {
            val principal = messageInfo.validateHeader() ?: messageInfo.validateSession()
            handler.handle(arrayOf(CallerPrincipalCallback(clientSubject, principal), GroupPrincipalCallback(clientSubject, null)))
            AuthStatus.SUCCESS
        } catch (ex: Exception) {
            Logger.getLogger("mx.com.inftel.codegen.jaspic.CodegenServerAuthContext").log(Level.SEVERE, "CodegenServerAuthContext.validateRequest", ex)
            messageInfo.sendError()
            AuthStatus.SEND_FAILURE
        }
    }

    private fun MessageInfo.validateHeader(): CodegenPrincipal? {
        val httpServletRequest = requestMessage as HttpServletRequest
        val authTokenHeader = httpServletRequest.getHeader(AUTHENTICATION_TOKEN_HEADER)
        if (authTokenHeader.isNullOrBlank()) {
            return null
        }
        val authToken: UUID? = try {
            UUID.fromString(authTokenHeader)
        } catch (ex: IllegalArgumentException) {
            null
        }
        if ((authToken == null) || (authToken.leastSignificantBits == 0L && authToken.mostSignificantBits == 0L)) {
            return null
        }
        return validateAuthToken(authToken)
    }

    private fun MessageInfo.validateSession(): CodegenPrincipal? {
        val httpServletRequest = requestMessage as HttpServletRequest
        val session = httpServletRequest.getSession(false)
                ?: return null
        val authToken: UUID? = session.getAttribute(AUTH_TOKEN_ATTRIBUTE) as? UUID
        if ((authToken == null) || (authToken.leastSignificantBits == 0L && authToken.mostSignificantBits == 0L)) {
            return null
        }
        return validateAuthToken(authToken)
    }

    override fun secureResponse(messageInfo: MessageInfo?, serviceSubject: Subject?): AuthStatus {
        return AuthStatus.SEND_SUCCESS
    }

    override fun cleanSubject(messageInfo: MessageInfo?, subject: Subject?) {
        // NO-OP
    }
}