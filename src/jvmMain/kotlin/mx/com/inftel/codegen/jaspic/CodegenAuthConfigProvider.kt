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

@file:Suppress("unused")

package mx.com.inftel.codegen.jaspic

import java.util.*
import javax.security.auth.callback.CallbackHandler
import javax.security.auth.message.config.AuthConfigProvider
import javax.security.auth.message.config.ClientAuthConfig
import javax.security.auth.message.config.ServerAuthConfig

class CodegenAuthConfigProvider(private val validateAuthToken: (UUID) -> CodegenPrincipal?) : AuthConfigProvider {

    override fun getClientAuthConfig(layer: String?, appContext: String?, handler: CallbackHandler?): ClientAuthConfig {
        throw UnsupportedOperationException()
    }

    override fun getServerAuthConfig(layer: String?, appContext: String?, handler: CallbackHandler): ServerAuthConfig {
        return CodegenServerAuthConfig(validateAuthToken, handler)
    }

    override fun refresh() {
        // NO-OP
    }
}