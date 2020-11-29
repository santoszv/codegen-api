package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class RelationNotFoundException : RuntimeException("Relation Not Found Exception")