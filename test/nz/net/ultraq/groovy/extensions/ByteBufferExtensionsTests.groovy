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

import java.nio.ByteBuffer

/**
 * Tests for the {@link ByteBufferExtensions} methods.
 * 
 * @author Emanuel Rabina
 */
class ByteBufferExtensionsTests extends Specification {

	def "#advance - Moves the buffer position ahead by the given amount"() {
		given:
			def buffer = ByteBuffer.allocate(100)
			buffer.position(5)
		when:
			buffer.advance(10)
		then:
			buffer.position() == 15
	}

	def "#put - copies data from the other buffer"() {
		given:
			def buffer = ByteBuffer.allocate(5)
			def source = ByteBuffer.wrap([1, 2, 3] as byte[])
		when:
			buffer.put(source, 2)
		then:
			buffer.array() == [1, 2, 0, 0, 0] as byte[]
		buffer.position() == 2
		source.position() == 2
	}

	def "#split - cuts the buffer into the specified numberof parts"() {
		given:
			def buffer = ByteBuffer.wrap([1, 2, 3, 4, 5] as byte[])
		when:
			def result = buffer.split(2)
		then:
			result.length == 3
			result[0].array() == [1, 2] as byte[]
			result[0].position() == 0
			result[1].array() == [3, 4] as byte[]
			result[1].position() == 0
			result[2].array() == [5] as byte[]
			result[2].position() == 0
			buffer.position() == 0
	}
}
