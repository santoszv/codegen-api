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

@file:Suppress("unused")

package mx.com.inftel.codegen.browser

import org.w3c.dom.Element

/**
 * Interface marker for an element wrapper
 */
@Deprecated("Deprecated")
interface ElementWrapper {

    companion object {

        /**
         * Gets the wrapped element.
         */
        fun getWrappedElement(elementWrapper: Any): Element {
            return elementWrapper.asDynamic()["kotlinWrappedElement$"].unsafeCast<Element>()
        }

        /**
         * Sets the wrapped element.
         */
        fun setWrappedElement(elementWrapper: Any, element: Element) {
            elementWrapper.asDynamic()["kotlinWrappedElement$"] = element
        }
    }
}