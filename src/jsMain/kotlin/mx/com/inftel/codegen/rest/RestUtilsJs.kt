package mx.com.inftel.codegen.rest

import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

external fun encodeURIComponent(str: String): String