package mx.com.inftel.codegen.browser

import org.w3c.dom.Element
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Wrapped element
 */
val ElementWrapper.wrappedElement: Element
    get() = ElementWrapper.getWrappedElement(this)

/**
 * Make a request with co-routines
 */
suspend fun XMLHttpRequest.aSend(body: Any): Unit = suspendCoroutine { cont ->
    onreadystatechange = {
        if (readyState == XMLHttpRequest.DONE) {
            cont.resume(Unit)
        }
    }
    if (body == Unit) {
        send()
    } else {
        send(body)
    }
}