/*
 * Copyright 2026, Emanuel Rabina (http://www.ultraq.net.nz/)
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
 * Extensions for the {@link Enum} class.
 *
 * @author Emanuel Rabina
 */
class EnumExtensions {

	/**
	 * Returns the next enum value in the sequence of enums of this type, wrapping
	 * back to the first enum when the end of the sequence is reached.
	 */
	static <E extends Enum> Enum<E> next(Enum<E> self) {

		return self.class.enumConstants[(self.ordinal() + 1) % self.class.enumConstants.length]
	}
}
