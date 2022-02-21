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

import java.util.concurrent.ArrayBlockingQueue

/**
 * Tests for the {@link BlockingQueueExtensions} methods.
 * 
 * @author Emanuel Rabina
 */
class BlockingQueueExtensionsTests extends Specification {

	def data = [1, 2, 3, 4]

	def "drain() is a shortcut for BlockingQueue.drainTo"() {
		given:
			def queue = new ArrayBlockingQueue(4, true, data)
		when:
			def result = queue.drain()
		then:
			def expected = []
			new ArrayBlockingQueue(4, true, data).drainTo(expected)
			result == expected
	}

	def "drain(int) is a shortcut for BlockingQueue.drainTo(int)"() {
		given:
			def queue = new ArrayBlockingQueue(4, true, data)
			def maxElements = 3
		when:
			def result = queue.drain(maxElements)
		then:
			def expected = []
			new ArrayBlockingQueue(4, true, data).drainTo(expected, maxElements)
			result == expected
	}
}
