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
 * Tests for instance methods on the {@code Object} class.
 * 
 * @author Emanuel Rabina
 */
class ObjectExtensionsTests extends Specification {

	private static class ObjectTest {
		String message
	}

	def "Sets and returns the result on the object"() {
		given:
			def key = 'message'
			def obj = new ObjectTest()
		when:
			def value = obj.getOrCreate(key) { ->
				return 'Hi!'
			}
		then:
			value == 'Hi!'
			obj[key] == 'Hi!'
	}

	def "If the property already exists, return its value"() {
		given:
			def property = 'message'
			def obj = new ObjectTest()
			obj[property] = 'Hello!'
		when:
			def result = obj.getOrCreate(property) { ->
				return 'Goodbye!'
			}
		then:
			result == 'Hello!'
	}
}
