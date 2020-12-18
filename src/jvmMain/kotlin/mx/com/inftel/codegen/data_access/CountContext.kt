@file:Suppress("unused")

package mx.com.inftel.codegen.data_access

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class CountContext<T>(val criteriaBuilder: CriteriaBuilder, val root: Root<T>) {

    val predicates: MutableList<Predicate> = mutableListOf()
}