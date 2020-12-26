@file:Suppress("unused")

package mx.com.inftel.codegen.templated

/**
 * Template for templated element
 */
@Deprecated("Deprecated")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class Template(val value: String)