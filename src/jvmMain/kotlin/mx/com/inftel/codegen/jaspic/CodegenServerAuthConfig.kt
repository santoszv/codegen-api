package mx.com.inftel.codegen.jaspic

import java.util.*
import javax.security.auth.Subject
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.message.MessageInfo
import javax.security.auth.message.config.ServerAuthConfig
import javax.security.auth.message.config.ServerAuthContext

class CodegenServerAuthConfig(private val validateAuthToken: (UUID) -> CodegenPrincipal?, private val handler: CallbackHandler) : ServerAuthConfig {

    override fun getMessageLayer(): String? {
        return null
    }

    override fun getAppContext(): String? {
        return null
    }

    override fun getAuthContextID(messageInfo: MessageInfo?): String? {
        return null
    }

    override fun refresh() {
        // NO-OP
    }

    override fun isProtected(): Boolean {
        return false
    }

    override fun getAuthContext(authContextID: String?, serviceSubject: Subject?, properties: MutableMap<Any?, Any?>?): ServerAuthContext {
        return CodegenServerAuthContext(validateAuthToken, handler)
    }
}