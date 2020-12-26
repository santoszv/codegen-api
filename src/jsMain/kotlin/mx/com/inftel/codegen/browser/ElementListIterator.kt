package mx.com.inftel.codegen.browser

/**
 * Children list iterator of templated element
 */
@Deprecated("Deprecated")
class ElementListIterator<T>(private val delegate: MutableListIterator<T>) : MutableListIterator<T> {

    override fun hasPrevious(): Boolean {
        return delegate.hasPrevious()
    }

    override fun nextIndex(): Int {
        return delegate.nextIndex()
    }

    override fun previous(): T {
        return delegate.previous()
    }

    override fun previousIndex(): Int {
        return delegate.previousIndex()
    }

    override fun add(element: T) {
        throw UnsupportedOperationException()
    }

    override fun hasNext(): Boolean {
        return delegate.hasNext()
    }

    override fun next(): T {
        return delegate.next()
    }

    override fun remove() {
        throw UnsupportedOperationException()
    }

    override fun set(element: T) {
        throw UnsupportedOperationException()
    }
}