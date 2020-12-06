package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class BadParameterException(message: String = "Bad Parameter Exception") : RuntimeException(message)