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

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import static org.assertj.core.api.Assertions.*

/**
 * Tests for the {@link StringExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class StringExtensionsTests {

	@ParameterizedTest
	@CsvSource([
		'/part1, part2',
		'/part1/, part2',
		'/part1, /part2',
		'/part1/, /part2'
	])
	void 'Normalizes duplicate separators'(String part1, String part2) {
		expect:
			assertThat(([part1, part2] as String[]).joinAndNormalize('/')).isEqualTo('/part1/part2')
	}

	@ParameterizedTest
	@ValueSource(strings = [
		'Turn to pascal case',
		'turn-to-pascal-case'
	])
	void "Convert from sentence case to PascalCase"(String input) {
		expect:
			assertThat(input.toPascalCase()).isEqualTo("TurnToPascalCase")
	}
}
