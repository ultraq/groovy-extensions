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
 * Tests for the {@link DataInputExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class DataInputExtensionsTests extends Specification {

	def data = [1, 2, 3, 4] as byte[]
	def inputStream = new DataInputStream(new ByteArrayInputStream(data))

	def "Reads the specified number of bytes"(int numBytes) {
		when:
			def result = inputStream.readBytes(numBytes)
		then:
			result == data[0..<numBytes]
		where:
			numBytes << [0, 1, 3]
	}

	def "Throws EOFException when numBytes exceeds available data"() {
		when:
			inputStream.readBytes(5)
		then:
			thrown(EOFException)
	}
}
