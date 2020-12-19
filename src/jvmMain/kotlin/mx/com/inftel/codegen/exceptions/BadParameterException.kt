package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class BadParameterException(message: String? = null) : RuntimeException(message)