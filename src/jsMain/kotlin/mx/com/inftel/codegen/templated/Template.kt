@file:Suppress("unused")

package mx.com.inftel.codegen.templated

/**
 * Template for templated element
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class Template(val value: String)