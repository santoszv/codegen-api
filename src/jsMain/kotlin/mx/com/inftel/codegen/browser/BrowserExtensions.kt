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
@Deprecated("Deprecated", ReplaceWith("ElementWrapper.getWrappedElement(this)"))
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