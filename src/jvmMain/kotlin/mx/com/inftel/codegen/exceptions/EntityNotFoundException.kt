package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class EntityNotFoundException : RuntimeException("Entity Not Found Exception")