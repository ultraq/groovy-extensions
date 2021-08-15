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

package nz.net.ultraq.extensions.groovy

import spock.lang.Specification

/**
 * Tests for the static extension methods to the {@code Math} class.
 * 
 * @author Emanuel Rabina
 */
class MathStaticExtensionsTests extends Specification {

	def "Clamps int values to the specified range"(int value) {
		expect:
			Math.clamp(value, 0, 10) >= 0
			Math.clamp(value, 0, 10) <= 10
		where:
			value << [5, -3, 22]
	}

	def "Clamps float values to the specified range"(float value) {
		expect:
			Math.clamp(value, 0f, 10f) >= 0f
			Math.clamp(value, 0f, 10f) <= 10f
		where:
			value << [1.5f, -3.2f, 22f]
	}
}
