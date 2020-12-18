@file:JvmName("JsfUtils")
@file:Suppress("unused")

package mx.com.inftel.codegen.jsf

import mx.com.inftel.codegen.servlet.setAuthToken
import java.util.*
import javax.faces.context.FacesContext
import javax.servlet.http.HttpServletRequest

fun FacesContext.setAuthToken(token: UUID) {
    val servletRequest = externalContext.request as HttpServletRequest
    servletRequest.setAuthToken(token)
}