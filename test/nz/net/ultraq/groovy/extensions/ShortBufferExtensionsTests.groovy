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

import java.nio.ShortBuffer

/**
 * Tests for the {@link ShortBufferExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class ShortBufferExtensionsTests {

	@Test
	void 'Moves the buffer position ahead by the given amount'() {
		given:
			var buffer = ShortBuffer.allocate(100).position(5)
		when:
			buffer.advance(10)
		then:
			assertThat(buffer.position()).isEqualTo(15)
	}
}
