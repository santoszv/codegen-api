@file:JvmName("CodegenConstants")

package mx.com.inftel.codegen

import kotlin.jvm.JvmName

const val ANTI_REPLAY_TOKEN_HEADER = "X-Anti-Replay-Token"
const val AUTHENTICATION_TOKEN_HEADER = "X-Authentication-Token"
const val EXCEPTION_NAME_HEADER = "X-Exception-Name"
const val FORWARDED_FOR_HEADER = "X-Forwarded-For"

const val NULL_ANTI_REPLAY_TOKEN = "00000000-0000-0000-0000-000000000000"
const val NULL_AUTHENTICATION_TOKEN = "00000000-0000-0000-0000-000000000000"