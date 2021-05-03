/*
 *    Copyright 2021 Santos Zatarain Vera
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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