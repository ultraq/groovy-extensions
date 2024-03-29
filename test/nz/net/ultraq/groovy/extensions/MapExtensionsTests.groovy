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

import spock.lang.Specification

/**
 * Tests for instance methods on the {@code Map} class.
 *
 * @author Emanuel Rabina
 */
class MapExtensionsTests extends Specification {

	def "Sets and returns the result on the map"() {
		given:
			def map = [:]
			def key = new Object()
		when:
			def value = map.getOrCreate(key) { ->
				return 'Hi!'
			}
		then:
			value == 'Hi!'
			map[key] == 'Hi!'
	}

	def "If the key already exists, return its value"() {
		given:
			def key = 'key'
			def value = 'Hello!'
			def map = [(key): value]
		when:
			def result = map.getOrCreate(key) { ->
				return 'Goodbye!'
			}
		then:
			result == value
	}
}
