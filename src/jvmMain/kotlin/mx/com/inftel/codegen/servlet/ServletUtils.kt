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

@file:JvmName("ServletUtils")

package mx.com.inftel.codegen.servlet

import java.io.IOException
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

fun HttpServletRequest.setAuthToken(token: UUID) {
    val session = getSession(true)
    session.setAttribute(AUTH_TOKEN_ATTRIBUTE, token)
}

fun HttpServletResponse.sendError() {
    try {
        sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
    } catch (ex: IOException) {
        Logger.getLogger("mx.com.inftel.codegen.servlet.ServletUtils").log(Level.WARNING, "Exception while sending error response", ex)
    }
}