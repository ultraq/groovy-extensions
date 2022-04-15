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

import java.util.concurrent.Future

/**
 * Extensions to the {@link Future} interface.
 * 
 * @author Emanuel Rabina
 */
class FutureExtensions {

	/**
	 * Call {@link Future#cancel} with a default argument of {@code false}.
	 * 
	 * @param self
	 * @return
	 */
	static boolean cancel(Future self) {

		return self.cancel(false)
	}
}
