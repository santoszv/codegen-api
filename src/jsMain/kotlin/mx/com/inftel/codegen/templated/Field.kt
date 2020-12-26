@file:Suppress("unused")

package mx.com.inftel.codegen.templated

/**
 * Field in template of templated element
 */
@Deprecated("Deprecated")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class Field(val value: String)