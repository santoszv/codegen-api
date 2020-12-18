@file:Suppress("unused")

package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class RelationNotFoundException(message: String = "Relation Not Found Exception") : RuntimeException(message)