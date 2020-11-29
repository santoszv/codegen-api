@file:JvmName("ServletUtils")

package mx.com.inftel.codegen.servlet

import java.io.IOException
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val AUTH_TOKEN_ATTRIBUTE = "mx.com.inftel.codegen.AUTH_TOKEN"

fun HttpServletRequest.setAuthToken(token: UUID) {
    val session = getSession(true)
    session.setAttribute(AUTH_TOKEN_ATTRIBUTE, token)
}

fun HttpServletResponse.sendError() {
    try {
        sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
    } catch (ex: IOException) {
        Logger.getLogger("mx.com.inftel.codegen.jaspic.JaspicUtils").log(Level.SEVERE, "HttpServletResponse.sendError", ex)
    }
}