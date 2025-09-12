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

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

/**
 * Tests for the static extension methods to the {@code Math} class.
 *
 * @author Emanuel Rabina
 */
class MathStaticExtensionsTests {

	@ParameterizedTest
	@CsvSource([
	  '1, 1',
	  '-3, 7',
	  '22, 2',
	  '10, 0'
	])
	def "Wraps int values to the specified range"(int value, int result) {
		expect:
			Math.wrap(value, 0, 10) == result
	}

	@ParameterizedTest
	@CsvSource([
	  '1.5, 1.5',
	  '-3.2, 6.8',
	  '22, 2.0',
	  '10, 0.0'
	])
	void 'Wraps float values to the specified range'(float value, float result) {
		expect:
			Math.wrap(value, 0f, 10f) == result
	}
}
