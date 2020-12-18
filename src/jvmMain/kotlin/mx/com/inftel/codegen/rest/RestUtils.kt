@file:JvmName("RestUtils")
@file:Suppress("unused", "RemoveExplicitTypeArguments")

package mx.com.inftel.codegen.rest

import mx.com.inftel.codegen.EXCEPTION_NAME_HEADER
import mx.com.inftel.codegen.data_access.MetaModelPath
import mx.com.inftel.codegen.dto.ExceptionDto
import mx.com.inftel.codegen.exceptions.BadParameterException
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

fun SecurityContext.checkUserPrincipal(): CodegenPrincipal {
    return userPrincipal as? CodegenPrincipal
        ?: throw newNotAuthorizedException()
}

fun SecurityContext.checkSuperuserPrincipal(): CodegenPrincipal {
    val codegenPrincipal = checkUserPrincipal()
    if (!codegenPrincipal.isSuperuser) {
        throw newForbiddenException()
    }
    return codegenPrincipal
}

fun newBadRequestException(message: String? = null): BadRequestException {
    val exceptionDto = ExceptionDto()
    exceptionDto.name = "javax.ws.rs.BadRequestException"
    exceptionDto.message = message ?: "Bad Request Exception"
    val response = Response.status(Response.Status.BAD_REQUEST)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_NAME_HEADER, exceptionDto.name)
            .entity(exceptionDto)
            .build()
    return BadRequestException(response)
}

fun newNotAuthorizedException(message: String? = null): NotAuthorizedException {
    val exceptionDto = ExceptionDto()
    exceptionDto.name = "javax.ws.rs.NotAuthorizedException"
    exceptionDto.message = message ?: "Not Authorized Exception"
    val response = Response.status(Response.Status.UNAUTHORIZED)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_NAME_HEADER, exceptionDto.name)
            .entity(exceptionDto)
            .build()
    return NotAuthorizedException(response)
}

fun newForbiddenException(message: String? = null): ForbiddenException {
    val exceptionDto = ExceptionDto()
    exceptionDto.name = "javax.ws.rs.ForbiddenException"
    exceptionDto.message = message ?: "Forbidden Exception"
    val response = Response.status(Response.Status.FORBIDDEN)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_NAME_HEADER, exceptionDto.name)
            .entity(exceptionDto)
            .build()
    return ForbiddenException(response)
}

fun newNotFoundException(message: String? = null): NotFoundException {
    val exceptionDto = ExceptionDto()
    exceptionDto.name = "javax.ws.rs.NotFoundException"
    exceptionDto.message = message ?: "Not Found Exception"
    val response = Response.status(Response.Status.NOT_FOUND)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .header(EXCEPTION_NAME_HEADER, exceptionDto.name)
            .entity(exceptionDto)
            .build()
    return NotFoundException(response)
}

fun MutableList<Predicate>.fillPredicates(predicates: List<String>, criteriaBuilder: CriteriaBuilder, root: Root<out Any>, dtoType: Class<out Any>) {
    for (predicate in predicates) {
        if (predicate.isBlank()) {
            throw BadParameterException()
        }
        val t = predicate.split(":", limit = 3)
        if (t.size != 3) {
            throw BadParameterException()
        }
        val operator = t[0]
        if (operator.isBlank()) {
            throw BadParameterException()
        }
        val propertyName = t[1]
        if (propertyName.isBlank()) {
            throw BadParameterException()
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
            else -> throw BadParameterException()
        }
    }
}

fun MutableList<Order>.fillOrders(orders: List<String>, criteriaBuilder: CriteriaBuilder, root: Root<out Any>, dtoType: Class<out Any>) {
    for (order in orders) {
        if (order.isBlank()) {
            throw BadParameterException()
        }
        val t = order.split(":", limit = 2).toTypedArray()
        if (t.size != 2) {
            throw BadParameterException()
        }
        val operator = t[0]
        if (operator.isBlank()) {
            throw BadParameterException()
        }
        val propertyName = t[1]
        if (operator.isBlank()) {
            throw BadParameterException()
        }
        when (operator) {
            "asc" -> add(criteriaBuilder.asc(dtoType.getMetamodelPath<Any>(root, propertyName)))
            "desc" -> add(criteriaBuilder.desc(dtoType.getMetamodelPath<Any>(root, propertyName)))
            else -> throw BadParameterException()
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
    throw BadParameterException()
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