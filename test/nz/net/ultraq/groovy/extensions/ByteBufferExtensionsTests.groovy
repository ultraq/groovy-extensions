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

import java.nio.ByteBuffer

/**
 * Tests for the {@link ByteBufferExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class ByteBufferExtensionsTests {

	@Test
	void '#markAndReset - Calls mark, executes the closure, and calls reset'() {
		given:
			var mockBuffer = mock(ByteBuffer)
		when:
			var result = mockBuffer.markAndReset { _ ->
				return 'Hello!'
			}
		then:
			verify(mockBuffer).mark()
			verify(mockBuffer).reset()
			assertThat(result).isEqualTo('Hello!')
	}

	@Test
	void '#markAndReset - Calls reset when an exception occurs'() {
		given:
			var mockBuffer = mock(ByteBuffer)
		when:
			var exception = catchException { ->
				mockBuffer.markAndReset { _ ->
					throw new Exception('Testing')
				}
			}
		then:
			assertThat(exception).isNotNull()
			verify(mockBuffer).mark()
			verify(mockBuffer).reset()
	}

	@Test
	void '#put - copies data from the other buffer'() {
		given:
			var buffer = ByteBuffer.allocate(5)
			var source = ByteBuffer.wrap([1, 2, 3] as byte[])
		when:
			buffer.put(source, 2)
		then:
			assertThat(buffer.array()).isEqualTo([1, 2, 0, 0, 0] as byte[])
			assertThat(buffer.position()).isEqualTo(2)
			assertThat(source.position()).isEqualTo(2)
	}

	@Test
	void '#split - cuts the buffer into the specified number of parts'() {
		given:
			var buffer = ByteBuffer.wrap([1, 2, 3, 4, 5] as byte[])
		when:
			var result = buffer.split(2)
		then:
			assertThat(result.length).isEqualTo(3)
			assertThat(result[0].array()).isEqualTo([1, 2] as byte[])
			assertThat(result[0].position()).isEqualTo(0)
			assertThat(result[1].array()).isEqualTo([3, 4] as byte[])
			assertThat(result[1].position()).isEqualTo(0)
			assertThat(result[2].array()).isEqualTo([5] as byte[])
			assertThat(result[2].position()).isEqualTo(0)
			assertThat(buffer.position()).isEqualTo(0)
	}
}
