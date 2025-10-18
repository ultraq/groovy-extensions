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

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import java.nio.ByteBuffer

/**
 * Instance method extensions to the {@code ByteBuffer} class.
 *
 * @author Emanuel Rabina
 */
class ByteBufferExtensions {

	/**
	 * An alias for {@link ByteBuffer#get} so that its shortened Groovy getter
	 * form looks consistent alongside the other shortened getters like short,
	 * int, etc.
	 */
	static byte getByte(ByteBuffer self) {

		return self.get()
	}

	/**
	 * Shorthand for {@code ByteBuffer.get() & 0xff}, ensuring that a byte value
	 * when made an int remains unsigned.
	 */
	static int getUnsignedByte(ByteBuffer self) {

		return self.get() & 0xff
	}

	/**
	 * Shorthand for {@code ByteBuffer.get(index) & 0xff}, ensuring that a byte
	 * value when made an int remains unsigned.
	 */
	static int getUnsignedByte(ByteBuffer self, int index) {

		return self.get(index) & 0xff
	}

	/**
	 * Shorthand for {@code ByteBuffer.getShort() & 0xffff}, ensuring that a short
	 * value when made an int remains unsigned.
	 */
	static int getUnsignedShort(ByteBuffer self) {

		return self.getShort() & 0xffff
	}

	/**
	 * Shorthand for {@code ByteBuffer.getShort(index) & 0xffff}, ensuring that a
	 * short value when made an int remains unsigned.
	 */
	static int getUnsignedShort(ByteBuffer self, int index) {

		return self.getShort(index) & 0xffff
	}

	/**
	 * Convenience method for calling {@link ByteBuffer#mark}, executing the
	 * closure, and then calling {@link ByteBuffer#reset} before returning.
	 */
	static <T> T markAndReset(ByteBuffer self,
		@ClosureParams(value = SimpleType, options = "java.lang.ByteBuffer") Closure<T> closure) {

		try {
			self.mark()
			return closure(self)
		}
		finally {
			self.reset()
		}
	}

	/**
	 * A relative bulk <i>put</i> method using another {@code ByteBuffer} but only
	 * reading up to {@code length} bytes from that buffer.  The position of both
	 * buffers will be increased by {@code length}.
	 */
	static ByteBuffer put(ByteBuffer self, ByteBuffer src, int length) {

		self.put(src.array(), src.position(), length)
		src.advance(length)
		return self
	}

	/**
	 * Split a buffer into several smaller buffers of the specified size.  If the
	 * size doesn't cleanly divide into the current buffer, then the final buffer
	 * will be smaller and contain the remaining bytes.
	 */
	static ByteBuffer[] split(ByteBuffer self, int length) {

		self.rewind()
		var buffers = new ByteBuffer[Math.ceil(self.limit() / length)].collect {
			var bufferSize = Math.min(length, self.remaining())
			return ByteBuffer.allocate(bufferSize).order(self.order())
				.put(self, bufferSize)
				.rewind()
		}
		self.rewind()
		return buffers
	}
}
