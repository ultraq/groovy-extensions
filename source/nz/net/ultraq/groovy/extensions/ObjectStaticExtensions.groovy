/* 
 * Copyright 2022, Emanuel Rabina (http://www.ultraq.net.nz/)
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
 * Static extensions to the base {@code Object} class.
 * 
 * @author Emanuel Rabina
 */
class ObjectStaticExtensions {

	/**
	 * Similar to {@code Object.with}, but with the ability to use a static class
	 * instead of an object instance so methods within the closure are invoked on
	 * the static class.
	 * 
	 * @param self
	 * @param delegate
	 * @param closure
	 * @return
	 */
	static <T> T with(Object self, @DelegatesTo.Target Class delegate,
		@DelegatesTo(strategy = Closure.DELEGATE_FIRST)
		Closure<T> closure) {

		closure.resolveStrategy = Closure.DELEGATE_FIRST
		closure.delegate = delegate
		return closure()
	}
}
