/*
 * Copyright 2025, Emanuel Rabina (http://www.ultraq.net.nz/)
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
 * Additional {@link String} methods.
 * <p>
 * For the case conversion methods, they can convert from any of the following
 * supported cases:
 * <ul>
 *   <li>kebab-case</li>
 *   <li>Sentence case</li>
 * </ul>
 *
 * @author Emanuel Rabina
 */
class StringExtensions {

	/**
	 * The same as a standard {@code join} method, but removes any double-ups of
	 * the separator in cases when some parts already contain it.  eg:
	 * <p>
	 * {@code ['/part1/', '/part2'].joinAndNormalize('/') == '/part1/part2'}
	 * <p>
	 * Useful for things like path segments in URLs or file systems.
	 */
	static String joinAndNormalize(String[] self, String separator) {

		return self.join(separator).replaceAll(/${separator}{2,}/, separator)
	}

	/**
	 * Convert a string from any of the supported cases to PascalCase.
	 */
	static String toPascalCase(String self) {

		return self.split(/[ -]/)
			.collect { part -> part.capitalize() }
			.join()
	}
}
