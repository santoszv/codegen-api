package mx.com.inftel.codegen.jaspic

import java.io.Serializable
import java.security.Principal
import java.util.*

class CodegenPrincipal(val token: UUID, val usuarioId: Long, val superUsuario: Boolean) : Principal, Serializable {

    override fun getName(): String {
        return "CodegenPrincipal(token=$token, usuarioId=$usuarioId, superUsuario=$superUsuario)"
    }
}