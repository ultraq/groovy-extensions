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

import spock.lang.Specification

/**
 * Tests for the {@link InputStreamExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class InputStreamExtensionsTests extends Specification {

	def inputStream = new ByteArrayInputStream([1, 2, 3, 4] as byte[])

	def "markAndReset - Calls mark, executes the closure, and calls reset"() {
		given:
			def mockStream = Mock(InputStream)
			def response = 'Hello!'
			Closure closure = Mock()
			closure.call(_) >> response
		when:
			def result = mockStream.markAndReset(2, closure)
		then:
			1 * mockStream.mark(2)
			1 * mockStream.reset()
			assert result == response
	}

	def "markAndReset - Passes the inputstream as a parameter to the closure"() {
		given:
			Closure closure = Mock()
		when:
			inputStream.markAndReset(Integer.MAX_VALUE, closure)
		then:
			1 * closure.call(inputStream)
	}

	def "markAndReset - Calls reset when an exception occurs"() {
		given:
			def mockStream = Mock(InputStream)
			Closure closure = Mock() {
				call(_) >> {
					throw new Exception('Testing')
				}
			}
		when:
			mockStream.markAndReset(2, closure)
		then:
			1 * mockStream.mark(2)
			thrown(Exception)
			1 * mockStream.reset()
	}

	def "withBufferedReader - Returns the result of the closure"() {
		given:
			def response = 'result'
			Closure closure = Mock()
			closure.call(_) >> response
		when:
			def result = inputStream.withBufferedReader(closure)
		then:
			assert result == response
	}

	def "withBufferedStream - Uses a buffered wrapper around an existing stream"() {
		given:
			Closure closure = Mock()
		when:
			inputStream.withBufferedStream(closure)
		then:
			1 * closure.call(_ as BufferedInputStream)
	}

	def "withBufferedStream - Returns the result of the closure"() {
		given:
			def response = 'result'
			Closure closure = Mock()
			closure.call(_) >> response
		when:
			def result = inputStream.withBufferedStream(closure)
		then:
			assert result == response
	}
}
