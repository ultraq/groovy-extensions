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

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FromString
import groovy.transform.stc.SimpleType

/**
 * Extensions to Java's {@link InputStream} classes.
 *
 * @author Emanuel Rabina
 */
class InputStreamExtensions {

	/**
	 * Convenience method for calling {@link InputStream#mark}, executing the
	 * closure, and then calling {@link InputStream#reset} before returning.
	 *
	 * @param self
	 * @param readLimit
	 * @param closure
	 * @return
	 */
	static <I extends InputStream, T> T markAndReset(I self, int readLimit,
		@ClosureParams(value = FromString, options = "I") Closure<T> closure) {

		try {
			self.mark(readLimit)
			def result = closure(self)
			return result
		}
		finally {
			self.reset()
		}
	}

	/**
	 * Wrap an input stream with a buffered reader and invoke the usual
	 * {@code withReader} method over it.
	 *
	 * @param self
	 * @param closure
	 * @return
	 */
	static <T> T withBufferedReader(InputStream self,
		@ClosureParams(value = SimpleType, options = 'java.io.BufferedReader') Closure<T> closure) {

		return new BufferedInputStream(self).withReader(closure)
	}

	/**
	 * Wrap an input stream with a buffered one and invoke the usual
	 * {@code withStream} method over it.
	 *
	 * @param self
	 * @param closure
	 * @return
	 */
	static <T> T withBufferedStream(InputStream self,
		@ClosureParams(value = SimpleType, options = 'java.io.BufferedInputStream') Closure<T> closure) {

		return new BufferedInputStream(self).withStream(closure)
	}
}
