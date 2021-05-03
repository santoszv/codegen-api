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

package mx.com.inftel.codegen.jaspic

import java.util.*
import javax.security.auth.Subject
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.message.MessageInfo
import javax.security.auth.message.config.ServerAuthConfig
import javax.security.auth.message.config.ServerAuthContext

class CodegenServerAuthConfig(private val validateAuthToken: (UUID) -> CodegenPrincipal?, private val handler: CallbackHandler) : ServerAuthConfig {

    override fun getMessageLayer(): String? {
        return null
    }

    override fun getAppContext(): String? {
        return null
    }

    override fun getAuthContextID(messageInfo: MessageInfo?): String? {
        return null
    }

    override fun refresh() {
        // NO-OP
    }

    override fun isProtected(): Boolean {
        return false
    }

    override fun getAuthContext(authContextID: String?, serviceSubject: Subject?, properties: MutableMap<Any?, Any?>?): ServerAuthContext {
        return CodegenServerAuthContext(validateAuthToken, handler)
    }
}