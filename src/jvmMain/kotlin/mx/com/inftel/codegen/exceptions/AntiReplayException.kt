package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class AntiReplayException(message: String = "Anti Replay Exception") : RuntimeException(message)