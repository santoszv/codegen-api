package mx.com.inftel.codegen.data_access

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Crud(val value: String)