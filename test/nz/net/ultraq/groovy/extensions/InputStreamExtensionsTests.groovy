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

import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.*
import static org.mockito.Mockito.*

/**
 * Tests for the {@link InputStreamExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class InputStreamExtensionsTests {

	InputStream inputStream = new ByteArrayInputStream([1, 2, 3, 4] as byte[])

	@Test
	void '#markAndReset - Calls mark, executes the closure, and calls reset'() {
		given:
			var mockStream = mock(InputStream)
			var response = 'Hello!'
		when:
			var result = mockStream.markAndReset(2) { _ ->
				return response
			}
		then:
			verify(mockStream).mark(2)
			verify(mockStream).reset()
			assertThat(result).isEqualTo(response)
	}

	@Test
	void '#markAndReset - Passes the inputstream as a parameter to the closure'() {
			var argument = null
			inputStream.markAndReset(Integer.MAX_VALUE) { stream ->
				argument = stream
			}
		then:
			assertThat(argument).isEqualTo(inputStream)
	}

	@Test
	void '#markAndReset - Calls reset when an exception occurs'() {
		given:
			var mockStream = mock(InputStream)
		when:
			var exception = catchException { ->
				mockStream.markAndReset(2) { _ ->
					throw new Exception('Testing')
				}
			}
		then:
			verify(mockStream).mark(2)
			assertThat(exception).hasMessage('Testing')
			verify(mockStream).reset()
	}

	@Test
	void '#withBufferedReader - Returns the result of the closure'() {
		given:
			var response = 'result'
		when:
			var result = inputStream.withBufferedReader { _ ->
				return response
			}
		then:
			assertThat(result).isEqualTo(response)
	}

	@Test
	void 'withBufferedStream - Uses a buffered wrapper around an existing stream'() {
		when:
			var argument = null
			inputStream.withBufferedStream() { stream ->
				argument = stream
			}
		then:
			assertThat(argument).isInstanceOf(BufferedInputStream)
	}

	@Test
	void '#withBufferedStream - Returns the result of the closure'() {
		given:
			var response = 'result'
		when:
			var result = inputStream.withBufferedStream() { _ ->
				return response
			}
		then:
			assertThat(result).isEqualTo(response)
	}
}
