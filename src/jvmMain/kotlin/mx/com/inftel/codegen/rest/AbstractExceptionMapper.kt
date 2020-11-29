package mx.com.inftel.codegen.rest

import mx.com.inftel.codegen.dto.ConstraintViolationDto
import mx.com.inftel.codegen.dto.ExceptionDto
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

abstract class AbstractExceptionMapper<T : Throwable?> : ExceptionMapper<T> {

    protected abstract fun getFqName(exception: T): String

    protected abstract fun getMessage(exception: T): String

    protected abstract fun getCause(exception: T): ExceptionDto?

    protected abstract fun getConstraintViolations(exception: T): List<ConstraintViolationDto>

    protected abstract fun getResponseStatus(exception: T): Response.Status

    override fun toResponse(exception: T): Response {
        val exceptionDto = ExceptionDto()
        exceptionDto.fqName = getFqName(exception)
        exceptionDto.message = getMessage(exception)
        exceptionDto.cause = getCause(exception)
        exceptionDto.constraintViolations = getConstraintViolations(exception)
        return Response.status(getResponseStatus(exception))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .header(EXCEPTION_CLASS_NAME_HEADER, exceptionDto.fqName)
                .entity(exceptionDto)
                .build()
    }
}