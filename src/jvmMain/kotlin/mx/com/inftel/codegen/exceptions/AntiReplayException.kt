package mx.com.inftel.codegen.exceptions

import javax.ejb.ApplicationException

@ApplicationException(rollback = true)
class AntiReplayException : RuntimeException("Anti Replay Exception")