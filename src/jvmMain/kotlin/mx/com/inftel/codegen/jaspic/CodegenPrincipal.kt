package mx.com.inftel.codegen.jaspic

import java.security.Principal
import java.util.*

class CodegenPrincipal(val token: UUID, val username: String, val superuser: Boolean, val id: Long?, val uuid: UUID?) : Principal {

    constructor(token: UUID, username: String, superuser: Boolean) : this(token, username, superuser, null, null)

    constructor(token: UUID, username: String, superuser: Boolean, id: Long) : this(token, username, superuser, id, null)

    constructor(token: UUID, username: String, superuser: Boolean, uuid: UUID) : this(token, username, superuser, null, uuid)

    override fun getName(): String {
        return username
    }

    override fun toString(): String {
        return "CodegenPrincipal(token=$token, username='$username', superuser=$superuser, id=$id, uuid=$uuid)"
    }
}