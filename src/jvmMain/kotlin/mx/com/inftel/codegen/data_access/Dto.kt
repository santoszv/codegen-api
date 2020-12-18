@file:Suppress("unused")

package mx.com.inftel.codegen.data_access

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Dto(val value: String)