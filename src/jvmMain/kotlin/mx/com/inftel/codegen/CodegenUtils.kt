@file:JvmName("CodegenUtils")

package mx.com.inftel.codegen

import java.util.*

val UUID.isNull: Boolean
    get() = (leastSignificantBits == 0L && mostSignificantBits == 0L)