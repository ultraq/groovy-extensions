/* 
 * Copyright 2020, Emanuel Rabina (http://www.ultraq.net.nz/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.net.ultraq.groovy.extensions

/**
 * Extensions to the base {@code Object} class.
 * 
 * @author Emanuel Rabina
 */
class ObjectExtensions {

	/**
	 * Retrieves a value from any object that supports the subscript operators
	 * (square bracket notation, implemented by the {@code getAt}/{@code putAt}
	 * methods).  If there is no value for the given key, then the {@code create}
	 * closure is executed and its return value is set on the object for the key,
	 * and returned to the calling code.
	 * 
	 * @param <T>
	 * @param self
	 * @param key
	 * @param create
	 * @return The value stored in the object by {@code key}.
	 */
	static <T> T getOrCreate(Object self, String key, Closure<T> create) {

		def value = self[key]
		if (!value) {
			self[key] = value = create()
		}
		return value
	}
}
