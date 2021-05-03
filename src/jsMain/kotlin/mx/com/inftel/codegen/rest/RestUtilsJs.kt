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