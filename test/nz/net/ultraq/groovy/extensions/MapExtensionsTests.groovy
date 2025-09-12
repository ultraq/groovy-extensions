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

import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.*

/**
 * Tests for instance methods on the {@code Map} class.
 *
 * @author Emanuel Rabina
 */
class MapExtensionsTests {

	@Test
	void 'Sets and returns the result on the map'() {
		given:
			var map = [:]
			var key = new Object()
		when:
			var value = map.getOrCreate(key) { ->
				return 'Hi!'
			}
		then:
			assertThat(value).isEqualTo('Hi!')
			assertThat(map).containsEntry(key, 'Hi!')
	}

	@Test
	void 'If the key already exists, return its value'() {
		given:
			var key = 'key'
			var value = 'Hello!'
			var map = [(key): value]
		when:
			var result = map.getOrCreate(key) { ->
				return 'Goodbye!'
			}
		then:
			assertThat(result).isEqualTo(value)
	}
}
