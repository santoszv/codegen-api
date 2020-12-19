package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class AntiReplayException(message: String? = null) : RuntimeException(message)