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

@file:Suppress("MemberVisibilityCanBePrivate", "unused")

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