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

package mx.com.inftel.codegen.jaspic

import java.security.Principal
import java.util.*

class CodegenPrincipal(val authToken: UUID, val userid: Long, val username: String, val isSuperuser: Boolean) : Principal {

    init {
        require(username.isNotBlank())
    }

    override fun getName(): String {
        return username
    }

    override fun toString(): String {
        return "CodegenPrincipal(authToken=$authToken, userid=$userid, username='$username', isSuperuser=$isSuperuser)"
    }
}