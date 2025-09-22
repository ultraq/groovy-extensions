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

	def "drain() receives all queued elements"() {
		expect:
			new ArrayBlockingQueue(4, true, data).drain() == data
	}

	def "drain() receives all queued elements - provided target list"() {
		expect:
			var target = []
			var result = new ArrayBlockingQueue(4, true, data).drain(target)
			result == data
			result === target
	}

	def "drain(int) receives the given number of elements"() {
		expect:
			new ArrayBlockingQueue(4, true, data).drain(3) == data.take(3)
	}

	def "drain(int) receives the given number of elements - provided target list"() {
		expect:
			var target = []
			var result = new ArrayBlockingQueue(4, true, data).drain(target, 3)
			result == data.take(3)
			result === target
	}
}
