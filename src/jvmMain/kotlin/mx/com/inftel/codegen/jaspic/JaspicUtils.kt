@file:JvmName("JaspicUtils")

package mx.com.inftel.codegen.jaspic

import mx.com.inftel.codegen.servlet.sendError
import java.util.logging.Level
import java.util.logging.Logger
import javax.security.auth.message.MessageInfo
import javax.servlet.http.HttpServletResponse

fun MessageInfo.sendError() {
    try {
        val servletResponse = responseMessage as HttpServletResponse
        servletResponse.sendError()
    } catch (ex: ClassCastException) {
        Logger.getLogger("mx.com.inftel.codegen.jaspic.JaspicUtils").log(Level.SEVERE, "MessageInfo.sendError", ex)
    }
}