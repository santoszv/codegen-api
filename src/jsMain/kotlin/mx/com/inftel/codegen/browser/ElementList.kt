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

@file:Suppress("DEPRECATION", "unused")

package mx.com.inftel.codegen.browser

import kotlinx.browser.document
import org.w3c.dom.Element

@Deprecated("Deprecated")
class ElementList<T>(parent: Any) : MutableList<T> {

    private val delegate = mutableListOf<T>()
    private val parent = if (parent is Element) parent else ElementWrapper.getWrappedElement(parent)

    override val size: Int
        get() = delegate.size

    override fun contains(element: T): Boolean {
        return delegate.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return delegate.containsAll(elements)
    }

    override fun get(index: Int): T {
        return delegate[index]
    }

    override fun indexOf(element: T): Int {
        return delegate.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return delegate.isEmpty()
    }

    override fun iterator(): MutableIterator<T> {
        return ElementListIterator(delegate.listIterator())
    }

    override fun lastIndexOf(element: T): Int {
        return delegate.lastIndexOf(element)
    }

    override fun add(element: T): Boolean {
        if (element != null) {
            val child = if (element is Element) element else ElementWrapper.getWrappedElement(element)
            parent.appendChild(child)
        }
        return delegate.add(element)
    }

    override fun add(index: Int, element: T) {
        throw UnsupportedOperationException()
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val fragment = document.createDocumentFragment()
        for (element in elements) {
            if (element != null) {
                val child = if (element is Element) element else ElementWrapper.getWrappedElement(element)
                fragment.appendChild(child)
            }
        }
        parent.appendChild(fragment)
        return delegate.addAll(elements)
    }

    override fun clear() {
        parent.textContent = null
        delegate.clear()
    }

    override fun listIterator(): MutableListIterator<T> {
        return ElementListIterator(delegate.listIterator())
    }

    override fun listIterator(index: Int): MutableListIterator<T> {
        return ElementListIterator(delegate.listIterator(index))
    }

    override fun remove(element: T): Boolean {
        if (element != null) {
            val child = if (element is Element) element else ElementWrapper.getWrappedElement(element)
            child.parentNode?.removeChild(child)
        }
        return delegate.remove(element)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (element != null) {
                val child = if (element is Element) element else ElementWrapper.getWrappedElement(element)
                child.parentNode?.removeChild(child)
            }
        }
        return delegate.removeAll(elements)
    }

    override fun removeAt(index: Int): T {
        val element = delegate.removeAt(index)
        if (element != null) {
            val child = if (element is Element) element else ElementWrapper.getWrappedElement(element)
            child.parentNode?.removeChild(child)
        }
        return element
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun set(index: Int, element: T): T {
        throw UnsupportedOperationException()
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        throw UnsupportedOperationException()
    }
}