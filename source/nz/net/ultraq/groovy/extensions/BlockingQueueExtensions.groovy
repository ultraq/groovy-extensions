/* 
 * Copyright 2019, Emanuel Rabina (http://www.ultraq.net.nz/)
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

import java.util.concurrent.BlockingQueue

/**
 * Extensions to the {@link BlockingQueue} interface.
 * 
 * @author Emanuel Rabina
 */
class BlockingQueueExtensions {

	/**
	 * Drains all available elements to a new list which is returned.
	 * 
	 * @param self
	 * @return
	 */
	static <E> List<E> drain(BlockingQueue<E> self) {

		def remaining = []
		self.drainTo(remaining)
		return remaining
	}

	/**
	 * Drain at most the given number of elements into a new list which is
	 * returned.
	 * 
	 * @param self
	 * @param maxElements
	 * @return
	 */
	static <E> List<E> drain(BlockingQueue<E> self, int maxElements) {

		def elements = []
		self.drainTo(elements, maxElements)
		return elements
	}
}
