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

import java.util.concurrent.Semaphore

/**
 * Tests for the {@link SemaphoreExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class SemaphoreExtensionsTests {

	@Test
	void 'Acquires the semaphore, executes the closure, then releases the semaphore'() {
		expect:
			var semaphore = new Semaphore(1)
			var result = semaphore.acquireAndRelease { ->
				assertThat(semaphore.tryAcquire()).isFalse()
				return 'Hello!'
			}
			assertThat(semaphore.tryAcquire()).isTrue()
			assertThat(result).isEqualTo('Hello!')
	}

	@Test
	void "Doesn't execute the closure if it fails to acquire the semaphore"() {
		expect:
			var semaphore = new Semaphore(1)
			semaphore.acquire()
			var result = semaphore.tryAcquireAndRelease { ->
				return 'Hello!'
			}
			assertThat(result).isNull()
	}
}
