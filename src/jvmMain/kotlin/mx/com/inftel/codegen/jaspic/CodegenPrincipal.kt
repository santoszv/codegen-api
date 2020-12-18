@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package mx.com.inftel.codegen.jaspic

import mx.com.inftel.codegen.NULL_UUID
import java.security.Principal
import java.util.*

class CodegenPrincipal(val authToken: UUID, val username: String, val isSuperuser: Boolean, val userId: Long = 0, val userUuid: UUID = UUID.fromString(NULL_UUID)) : Principal {

    override fun getName(): String {
        return username
    }

    override fun toString(): String {
        return "CodegenPrincipal(authToken=$authToken, username='$username', isSuperuser=$isSuperuser, userId=$userId, userUuid=$userUuid)"
    }
}