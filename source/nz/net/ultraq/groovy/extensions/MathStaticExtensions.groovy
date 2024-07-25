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

/**
 * Static method extensions to the {@code Math} class.
 *
 * @author Emanuel Rabina
 */
class MathStaticExtensions {

	/**
	 * Clamp an {@code int} value to the given range.  Both lower and upper values
	 * are inclusive.
	 *
	 * @deprecated This method is part of the {@code Math} class in Java 21.
	 * @param self
	 * @param value
	 * @param lower
	 * @param upper
	 * @return
	 */
	static int clamp(Math self, int value, int lower, int upper) {

		return Math.min(Math.max(lower, value), upper)
	}

	/**
	 * Clamp a {@code float} value to the given range.  Both lower and upper values
	 * are inclusive.
	 *
	 * @deprecated This method is part of the {@code Math} class in Java 21.
	 * @param self
	 * @param value
	 * @param lower
	 * @param upper
	 * @return
	 */
	static float clamp(Math self, float value, float lower, float upper) {

		return Math.min(Math.max(lower, value), upper)
	}

	/**
	 * Wrap an {@code int} value within a certain range.  The lower value is
	 * inclusive, whereas the upper value is not.
	 *
	 * @param self
	 * @param value
	 * @param lower
	 * @param upper
	 * @return
	 */
	static int wrap(Math self, int value, int lower, int upper) {

		// @formatter:off
		return value < lower ? wrap(self, upper - (lower - value), lower, upper) :
		       value >= upper ? wrap(self, lower + (value - upper), lower, upper) :
		       value
		// @formatter:on
	}

	/**
	 * Wrap a {@code float} value within a certain range.  The lower value is
	 * inclusive, whereas the upper value is not.
	 *
	 * @param self
	 * @param value
	 * @param lower
	 * @param upper
	 * @return
	 */
	static float wrap(Math self, float value, float lower, float upper) {

		// @formatter:off
		return value < lower ? wrap(self, upper - (lower - value) as float, lower, upper) :
		       value >= upper ? wrap(self, lower + (value - upper) as float, lower, upper) :
		       value
		// @formatter:on
	}
}
