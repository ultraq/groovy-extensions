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

import java.util.concurrent.Semaphore

/**
 * Extension methods for the Semaphore concurrency utility.
 *
 * @author Emanuel Rabina
 */
class SemaphoreExtensions {

	/**
	 * Acquire and release a single permit around the given closure.
	 *
	 * @param self
	 * @param closure
	 * @return
	 */
	static <T> T acquireAndRelease(Semaphore self, Closure<T> closure) {

		try {
			self.acquire()
			return closure()
		}
		finally {
			self.release()
		}
	}

	/**
	 * Using {@link Semaphore#tryAcquire}, execute the closure and release the
	 * semaphore if {@code tryAcquire} returned {@code true}.
	 *
	 * @param self
	 * @param closure
	 * @return
	 *   The result of executing the closure if {@code tryAcquire} returns
	 *   {@code true}, otherwise {@code null}.
	 */
	static <T> T tryAcquireAndRelease(Semaphore self, Closure<T> closure) {

		if (self.tryAcquire()) {
			try {
				return closure()
			}
			finally {
				self.release()
			}
		}
		return null
	}
}
