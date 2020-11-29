package mx.com.inftel.codegen.jaspic

import java.util.*
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.message.config.AuthConfigProvider
import javax.security.auth.message.config.ClientAuthConfig
import javax.security.auth.message.config.ServerAuthConfig

class CodegenAuthConfigProvider(private val validateAuthToken: (UUID) -> CodegenPrincipal?) : AuthConfigProvider {

    override fun getClientAuthConfig(layer: String?, appContext: String?, handler: CallbackHandler?): ClientAuthConfig {
        throw UnsupportedOperationException()
    }

    override fun getServerAuthConfig(layer: String?, appContext: String?, handler: CallbackHandler): ServerAuthConfig {
        return CodegenServerAuthConfig(validateAuthToken, handler)
    }

    override fun refresh() {
        // NO-OP
    }
}