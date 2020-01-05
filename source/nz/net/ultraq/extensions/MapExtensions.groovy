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

package nz.net.ultraq.extensions

/**
 * Extensions to the {@code Map} interface.
 * 
 * @author Emanuel Rabina
 */
class MapExtensions {

	/**
	 * Very similar to Groovy's {@code Map.get(Object key, Object defaultValue)}
	 * method, but allows the default value to be created in a closure.
	 * 
	 * @param <T>
	 * @param self
	 * @param key
	 * @param create
	 * @return The value stored in the object by {@code key}.
	 */
	static <K,V> V getOrCreate(Map<K,V> self, K key, Closure<V> create) {

		def value = self[key]
		if (!value) {
			self[key] = value = create()
		}
		return value
	}
}
