@file:Suppress("unused")

package mx.com.inftel.codegen.dto

import kotlinx.serialization.Serializable

/**
 * Path DTO
 */
@Serializable
class PathDto {

    /**
     * Name of path
     */
    var name: String = ""

    /**
     * Kind of path
     */
    var kind: String = ""
}