package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class EntityNotFoundException(message: String = "Entity Not Found Exception") : RuntimeException(message)