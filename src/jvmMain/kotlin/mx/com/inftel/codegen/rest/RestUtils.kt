@file:JvmName("RestUtils")

package mx.com.inftel.codegen.rest

import mx.com.inftel.codegen.data_access.MetaModelPath
import mx.com.inftel.codegen.dto.ExceptionDto
import mx.com.inftel.codegen.jaspic.CodegenPrincipal
import java.beans.Introspector
import java.lang.reflect.Modifier
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.persistence.criteria.*
import javax.ws.rs.BadRequestException
import javax.ws.rs.ForbiddenException
import javax.ws.rs.NotAuthorizedException
import javax.ws.rs.NotFoundException
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext

const val GENERATE_TOKEN_PROPERTY = "mx.com.inftel.codegen.GENERATE_TOKEN"

fun SecurityContext.checkUsuario(): CodegenPrincipal {
    val principal = userPrincipal as? CodegenPrincipal ?: throw newNotAuthorizedException("Not Authorized Exception")
    return principal
}

fun SecurityContext.checkSuperUsuario(): CodegenPrincipal {
    val principal = checkUsuario()
    if (!principal.superUsuario) {
        throw newForbiddenException("Forbidden Exception")
    }
    return principal
}

fun newBadRequestException(message: String?): BadRequestException {
    val exceptionDto = ExceptionDto()
    exceptionDto.fqName = BadRequestException::class.java.name
    exceptionDto.message = message!!
    val response = Response.status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_CLASS_NAME_HEADER, exceptionDto.fqName)
            .entity(exceptionDto)
            .build()
    return BadRequestException(response)
}

fun newNotAuthorizedException(message: String?): NotAuthorizedException {
    val exceptionDto = ExceptionDto()
    exceptionDto.fqName = NotAuthorizedException::class.java.name
    exceptionDto.message = message!!
    val response = Response.status(Response.Status.UNAUTHORIZED)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_CLASS_NAME_HEADER, exceptionDto.fqName)
            .entity(exceptionDto)
            .build()
    return NotAuthorizedException(response)
}

fun newForbiddenException(message: String?): ForbiddenException {
    val exceptionDto = ExceptionDto()
    exceptionDto.fqName = ForbiddenException::class.java.name
    exceptionDto.message = message!!
    val response = Response.status(Response.Status.FORBIDDEN)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_CLASS_NAME_HEADER, exceptionDto.fqName)
            .entity(exceptionDto)
            .build()
    return ForbiddenException(response)
}

fun newNotFoundException(message: String?): NotFoundException {
    val exceptionDto = ExceptionDto()
    exceptionDto.fqName = NotFoundException::class.java.name
    exceptionDto.message = message!!
    val response = Response.status(Response.Status.NOT_FOUND)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_CLASS_NAME_HEADER, exceptionDto.fqName)
            .entity(exceptionDto)
            .build()
    return NotFoundException(response)
}

fun MutableList<Predicate>.fillPredicates(predicates: List<String>, criteriaBuilder: CriteriaBuilder, root: Root<out Any>, dtoType: Class<out Any>) {
    for (predicate in predicates) {
        if (predicate.isBlank()) {
            throw BadParameterException("Bad Parameter Exception")
        }
        val t = predicate.split(":", limit = 3)
        if (t.size != 3) {
            throw BadParameterException("Bad Parameter Exception")
        }
        val operator = t[0]
        if (operator.isBlank()) {
            throw BadParameterException("Bad Parameter Exception")
        }
        val propertyName = t[1]
        if (propertyName.isBlank()) {
            throw BadParameterException("Bad Parameter Exception")
        }
        val valueAsString = t[2]
        when (operator) {
            "isNull" -> {
                val metamodelPath = dtoType.getMetamodelPath<Any>(root, propertyName)
                add(criteriaBuilder.isNull(metamodelPath))
            }
            "isNotNull" -> {
                val metamodelPath = dtoType.getMetamodelPath<Any>(root, propertyName)
                add(criteriaBuilder.isNotNull(metamodelPath))
            }
            "equal" -> {
                val metamodelPath = dtoType.getMetamodelPath<Any>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<Any>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.equal(metamodelPath, valueOrNull))
            }
            "notEqual" -> {
                val metamodelPath = dtoType.getMetamodelPath<Any>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<Any>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.notEqual(metamodelPath, valueOrNull))
            }
            "greaterThan" -> {
                val metamodelPath = dtoType.getMetamodelPath<Comparable<Any>>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<Comparable<Any>>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.greaterThan<Comparable<Any>>(metamodelPath, valueOrNull))
            }
            "greaterThanOrEqualTo" -> {
                val metamodelPath = dtoType.getMetamodelPath<Comparable<Any>>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<Comparable<Any>>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.greaterThanOrEqualTo<Comparable<Any>>(metamodelPath, valueOrNull))
            }
            "lessThan" -> {
                val metamodelPath = dtoType.getMetamodelPath<Comparable<Any>>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<Comparable<Any>>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.lessThan<Comparable<Any>>(metamodelPath, valueOrNull))
            }
            "lessThanOrEqualTo" -> {
                val metamodelPath = dtoType.getMetamodelPath<Comparable<Any>>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<Comparable<Any>>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.lessThanOrEqualTo<Comparable<Any>>(metamodelPath, valueOrNull))
            }
            "like" -> {
                val metamodelPath = dtoType.getMetamodelPath<String>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<String>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.like(metamodelPath, valueOrNull))
            }
            "notLike" -> {
                val metamodelPath = dtoType.getMetamodelPath<String>(root, propertyName)
                val valueOrNull = valueAsString.valueOrNull<String>(metamodelPath.model.bindableJavaType)
                add(criteriaBuilder.notLike(metamodelPath, valueOrNull))
            }
            else -> throw BadParameterException("Bad Parameter Exception")
        }
    }
}

fun MutableList<Order>.fillOrders(orders: List<String>, criteriaBuilder: CriteriaBuilder, root: Root<out Any>, dtoType: Class<out Any>) {
    for (order in orders) {
        if (order.isBlank()) {
            throw BadParameterException("Bad Parameter Exception")
        }
        val t = order.split(":", limit = 2).toTypedArray()
        if (t.size != 2) {
            throw BadParameterException("Bad Parameter Exception")
        }
        val operator = t[0]
        if (operator.isBlank()) {
            throw BadParameterException("Bad Parameter Exception")
        }
        val propertyName = t[1]
        if (operator.isBlank()) {
            throw BadParameterException("Bad Parameter Exception")
        }
        when (operator) {
            "asc" -> add(criteriaBuilder.asc(dtoType.getMetamodelPath<Any>(root, propertyName)))
            "desc" -> add(criteriaBuilder.desc(dtoType.getMetamodelPath<Any>(root, propertyName)))
            else -> throw BadParameterException("Bad Parameter Exception")
        }
    }
}

private fun <T : Any> Class<out Any>.getMetamodelPath(root: Root<out Any>, propertyName: String): Path<T> {
    for (method in methods) {
        if (!Modifier.isStatic(method.modifiers)) {
            val methodName = method.name
            val decapName = when {
                methodName == "getClass" -> continue
                methodName.startsWith("get") && methodName.length > 3 -> Introspector.decapitalize(methodName.substring(3))
                methodName.startsWith("is") && methodName.length > 2 -> Introspector.decapitalize(methodName.substring(2))
                else -> continue
            }
            if (decapName == propertyName) {
                val annotation = method.getAnnotation(MetaModelPath::class.java)
                val components = annotation.value.split(".")
                var result: Path<T> = root.get(components[0])
                var i = 1
                val n = components.size
                while (i < n) {
                    result = result.get(components[i++])
                }
                return result
            }
        }
    }
    throw BadParameterException("Bad Parameter Exception")
}

@Suppress("UNCHECKED_CAST")
private fun <T : Any> String.valueOrNull(bindableJavaType: Class<out Any>): T? {
    return when (bindableJavaType.name) {
        "int" -> toIntOrNull() as? T
        "long" -> toLongOrNull() as? T
        "float" -> toFloatOrNull() as? T
        "double" -> toDoubleOrNull() as? T
        "boolean" -> toBoolean() as? T
        "java.lang.Integer" -> toIntOrNull() as? T
        "java.lang.Long" -> toLongOrNull() as? T
        "java.lang.Float" -> toFloatOrNull() as? T
        "java.lang.Double" -> toDoubleOrNull() as? T
        "java.lang.Boolean" -> toBoolean() as? T
        "java.lang.String" -> this as? T
        "java.util.UUID" -> try {
            UUID.fromString(this) as? T
        } catch (ex: IllegalArgumentException) {
            null
        }
        "java.time.LocalDate" -> try {
            LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(this)) as? T
        } catch (ex: Exception) {
            null
        }
        "java.time.LocalDateTime" -> try {
            LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(this)) as? T
        } catch (ex: Exception) {
            null
        }
        "java.time.LocalTime" -> try {
            LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(this)) as? T
        } catch (ex: Exception) {
            null
        }
        else -> null
    }
}