package mx.com.inftel.codegen.rest

import mx.com.inftel.codegen.dto.ConstraintViolationDto
import mx.com.inftel.codegen.dto.ExceptionDto
import java.util.logging.Level
import java.util.logging.Logger
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

abstract class AbstractExceptionMapper<T : Exception> : ExceptionMapper<T> {

    protected abstract fun isLogged(exception: T): Boolean

    protected abstract fun getLoggerName(exception: T): String

    protected abstract fun getLoggerLevel(exception: T): Level

    protected abstract fun getLoggerMessage(exception: T): String

    protected abstract fun getExceptionName(exception: T): String

    protected abstract fun getExceptionMessage(exception: T): String

    protected abstract fun getExceptionCause(exception: T): ExceptionDto?

    protected abstract fun getConstraintViolations(exception: T): List<ConstraintViolationDto>

    protected abstract fun getResponseStatus(exception: T): Response.Status

    override fun toResponse(exception: T): Response {
        if (isLogged(exception)) {
            val logger = Logger.getLogger(getLoggerName(exception))
            logger.log(getLoggerLevel(exception), getLoggerMessage(exception), exception)
        }
        val exceptionDto = ExceptionDto()
        exceptionDto.name = getExceptionName(exception)
        exceptionDto.message = getExceptionMessage(exception)
        exceptionDto.cause = getExceptionCause(exception)
        exceptionDto.constraintViolations = getConstraintViolations(exception)
        return Response.status(getResponseStatus(exception))
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_NAME_HEADER, exceptionDto.name)
            .entity(exceptionDto)
            .build()
    }
}