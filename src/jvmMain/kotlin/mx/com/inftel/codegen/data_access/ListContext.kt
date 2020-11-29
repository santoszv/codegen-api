package mx.com.inftel.codegen.data_access

import javax.persistence.LockModeType
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Order
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class ListContext<T>(val criteriaBuilder: CriteriaBuilder, val root: Root<T>) {

    var firstResult = -1
    var maxResults = -1
    var lockMode: LockModeType? = null
    val predicates: MutableList<Predicate> = mutableListOf()
    val orders: MutableList<Order> = mutableListOf()
}