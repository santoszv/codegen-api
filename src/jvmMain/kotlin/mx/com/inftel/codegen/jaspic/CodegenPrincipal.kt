package mx.com.inftel.codegen.jaspic

import java.security.Principal
import java.util.*

class CodegenPrincipal(val authToken: UUID, val username: String, val isSuperuser: Boolean, val userId: Long?, val userUuid: UUID?) : Principal {

    constructor(token: UUID, username: String, isSuperuser: Boolean) : this(token, username, isSuperuser, null, null)

    constructor(token: UUID, username: String, isSuperuser: Boolean, userId: Long) : this(token, username, isSuperuser, userId, null)

    constructor(token: UUID, username: String, isSuperuser: Boolean, userUuid: UUID) : this(token, username, isSuperuser, null, userUuid)

    override fun getName(): String {
        return username
    }

    override fun toString(): String {
        return "CodegenPrincipal(authToken=$authToken, username='$username', isSuperuser=$isSuperuser, userId=$userId, userUuid=$userUuid)"
    }
}