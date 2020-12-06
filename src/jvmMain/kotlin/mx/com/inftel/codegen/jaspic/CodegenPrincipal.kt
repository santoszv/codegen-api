package mx.com.inftel.codegen.jaspic

import java.security.Principal
import java.util.*

class CodegenPrincipal(val token: UUID, val username: String, val superuser: Boolean) : Principal {

    override fun getName(): String {
        return username
    }

    override fun toString(): String {
        return "CodegenPrincipal(token=$token, username=$username, superuser=$superuser)"
    }
}