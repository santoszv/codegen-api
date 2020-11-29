package mx.com.inftel.codegen.dto

import kotlinx.serialization.Serializable

/**
 * Constraint violation DTO
 */
@Serializable
class ConstraintViolationDto {

    /**
     * Message of constraint violation
     */
    var message: String = ""

    /**
     * Path of constraint violation
     */
    var path: List<PathDto> = emptyList()
}