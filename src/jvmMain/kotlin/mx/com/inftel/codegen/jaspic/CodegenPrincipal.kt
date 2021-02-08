@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package mx.com.inftel.codegen.jaspic

import java.security.Principal
import java.util.*

class CodegenPrincipal(val authToken: UUID, val userid: Long, val username: String, val isSuperuser: Boolean) : Principal {

    init {
        require(username.isNotBlank())
    }

    override fun getName(): String {
        return username
    }

    override fun toString(): String {
        return "CodegenPrincipal(authToken=$authToken, userid=$userid, username='$username', isSuperuser=$isSuperuser)"
    }
}