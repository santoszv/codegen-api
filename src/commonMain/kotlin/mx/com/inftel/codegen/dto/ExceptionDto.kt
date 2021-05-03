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