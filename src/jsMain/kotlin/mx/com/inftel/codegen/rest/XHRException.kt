package mx.com.inftel.codegen.rest

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import mx.com.inftel.codegen.dto.ExceptionDto
import org.w3c.xhr.XMLHttpRequest

/**
 * Exception for states not in range 2XX
 */
class XHRException(val xhr: XMLHttpRequest) : RuntimeException() {

    override val message: String?
        get() = state?.message

    val state: ExceptionDto? by lazy {
        if (xhr.getResponseHeader("Content-Type") != "application/json") null else try {
            Json.Default.decodeFromString(ExceptionDto.serializer(), xhr.responseText)
        } catch (e: SerializationException) {
            null
        }
    }

    val status: Int by lazy {
        xhr.status.toInt()
    }
}