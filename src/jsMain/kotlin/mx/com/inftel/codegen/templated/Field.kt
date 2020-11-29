package mx.com.inftel.codegen.templated

/**
 * Field in template of templated element
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.PROPERTY_SETTER)
annotation class Field(val value: String)