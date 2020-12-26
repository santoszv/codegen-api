@file:Suppress("DEPRECATION", "unused")

package mx.com.inftel.codegen.browser

import org.w3c.dom.Element
import org.w3c.dom.ItemArrayLike
import org.w3c.dom.Node
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Wrapped element
 */
val ElementWrapper.wrappedElement: Element
    get() = ElementWrapper.getWrappedElement(this)

@Deprecated("Deprecated")
fun ItemArrayLike<Node>.asElementList(): List<Element> = object : AbstractList<Element>() {

    override val size: Int get() = this@asElementList.length

    override fun get(index: Int): Element = when (index) {
        in 0..lastIndex -> this@asElementList.item(index).unsafeCast<Element>()
        else -> throw IndexOutOfBoundsException("index $index is not in range [0..$lastIndex]")
    }
}

/**
 * Make a request with co-routines
 */
@Deprecated("Deprecated")
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