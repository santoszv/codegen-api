@file:Suppress("unused")

package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class EntityNotFoundException(message: String? = null) : RuntimeException(message)