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

import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.*

import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Tests for the {@link ByteBufferStaticExtensions} extension module.
 *
 * @author Emanuel Rabina
 */
class ByteBufferStaticExtensionsTests {

	@Test
	void '#allocateNative - returns a natively ordered buffer'() {
		expect:
			assertThat(ByteBuffer.allocateNative(8).order()).isEqualTo(ByteOrder.nativeOrder())
	}

	@Test
	void '#fromBuffers - combines data from several buffers'() {
		given:
			var buffer1 = ByteBuffer.wrapNative([1, 2, 3] as byte[])
			var buffer2 = ByteBuffer.wrapNative([4, 5, 6] as byte[])
		when:
			var result = ByteBuffer.fromBuffers(buffer1, buffer2)
		then:
			assertThat(result.position()).isEqualTo(0)
			assertThat(result.array()).isEqualTo([1, 2, 3, 4, 5, 6] as byte[])
			assertThat(buffer1.position()).isEqualTo(3) // The inputs are advanced
			assertThat(buffer2.position()).isEqualTo(3)
	}

	@Test
	void '#wrapNative - returns a natively ordered buffer'() {
		expect:
			assertThat(ByteBuffer.wrapNative([0x0f] as byte[]).order()).isEqualTo(ByteOrder.nativeOrder())
	}

	@Test
	void '#wrapNative - with offset & length returns a natively ordered buffer'() {
		expect:
			assertThat(ByteBuffer.wrapNative([0xca, 0xfe] as byte[], 0, 2).order()).isEqualTo(ByteOrder.nativeOrder())
	}
}
