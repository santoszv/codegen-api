@file:Suppress("unused")

package mx.com.inftel.codegen.browser

import org.w3c.dom.Element

/**
 * Interface marker for an element wrapper
 */
interface ElementWrapper {

    companion object {

        /**
         * Gets the wrapped element.
         */
        fun getWrappedElement(elementWrapper: Any): Element {
            return elementWrapper.asDynamic()["kotlinWrappedElement$"] as Element
        }

        /**
         * Sets the wrapped element.
         */
        fun setWrappedElement(elementWrapper: Any, element: Element) {
            elementWrapper.asDynamic()["kotlinWrappedElement$"] = element
        }
    }
}