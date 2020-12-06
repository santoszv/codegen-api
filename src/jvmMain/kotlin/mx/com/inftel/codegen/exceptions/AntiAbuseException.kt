package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class AntiAbuseException : RuntimeException("Anti Abuse Exception")