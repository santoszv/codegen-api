/*
 *    Copyright 2021 Santos Zatarain Vera
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

@file:Suppress("unused")

package mx.com.inftel.codegen.rest

import mx.com.inftel.codegen.EXCEPTION_NAME_HEADER
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