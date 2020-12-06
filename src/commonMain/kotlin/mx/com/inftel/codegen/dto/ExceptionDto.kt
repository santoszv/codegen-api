package mx.com.inftel.codegen.dto

import kotlinx.serialization.Serializable

/**
 * Exception DTO
 */
@Serializable
class ExceptionDto {

    /**
     * Fully qualified name of exception
     */
    var name: String = ""

    /**
     * Message of exception
     */
    var message: String = ""

    /**
     * Cause of exception
     */
    var cause: ExceptionDto? = null

    /**
     * Constraint violations
     */
    var constraintViolations: List<ConstraintViolationDto> = emptyList()
}