/* 
 * Copyright 2016, Emanuel Rabina (http://www.ultraq.net.nz/)
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
import java.nio.ByteOrder

/**
 * Tests for the {@link ByteBufferStaticExtensions} extension module.
 * 
 * @author Emanuel Rabina
 */
class ByteBufferStaticExtensionsTests extends Specification {

	def "allocateNative returns a natively ordered buffer"() {
		expect:
			ByteBuffer.allocateNative(8).order() == ByteOrder.nativeOrder()
	}

	def "fromBuffers combines data from several buffers"() {
		given:
			def buffer1 = ByteBuffer.wrapNative([1, 2, 3] as byte[])
			def buffer2 = ByteBuffer.wrapNative([4, 5, 6] as byte[])
		when:
			def result = ByteBuffer.fromBuffers(buffer1, buffer2)
		then:
			result.position() == 0
			result.array() == [1, 2, 3, 4, 5, 6] as byte[]
			buffer1.position() == 3 // The inputs are advanced
			buffer2.position() == 3
	}

	def "wrapNative returns a natively ordered buffer"() {
		expect:
			ByteBuffer.wrapNative([0x0f] as byte[]).order() == ByteOrder.nativeOrder()
	}

	def "wrapNative with offset & length returns a natively ordered buffer"() {
		expect:
			ByteBuffer.wrapNative([0xca, 0xfe] as byte[], 0, 2).order() == ByteOrder.nativeOrder()
	}
}
