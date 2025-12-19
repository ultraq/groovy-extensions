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

import spock.lang.Specification

/**
 * Tests for the {@link StringExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class StringExtensionsTests extends Specification {

	def "Normalizes duplicate separators"(String[] input) {
		expect:
			input.joinAndNormalize('/') == '/part1/part2'
		where:
			input << [
				['/part1', 'part2'],
				['/part1/', 'part2'],
				['/part1', '/part2'],
				['/part1/', '/part2']
			]
	}

	def "Leaves duplicates of the separator character within existing strings alone"() {
		expect:
			['https://www.example.com/', '/path-to-something'].joinAndNormalize('/') == 'https://www.example.com/path-to-something'
	}

	def "Convert to kebab-case"(String input) {
		expect:
			input.toKebabCase() == 'turn-to-kebab-case'
		where:
			input << [
				'turn-to-kebab-case',
				'TurnToKebabCase',
				'Turn to kebab case',
				'Turn To Kebab Case'
			]
	}

	def "Convert to PascalCase"(String input) {
		expect:
			input.toPascalCase() == 'TurnToPascalCase'
		where:
			input << [
				'turn-to-pascal-case',
				'TurnToPascalCase',
				'Turn to pascal case',
				'Turn To Pascal Case'
			]
	}

	def "Convert to Sentence case"(String input) {
		expect:
			input.toSentenceCase() == 'Turn to sentence case'
		where:
			input << [
				'turn-to-sentence-case',
				'TurnToSentenceCase',
				'Turn to sentence case',
				'Turn To Sentence Case'
			]
	}

	def "Convert to Sentence case (capitalized)"(String input) {
		expect:
			input.toSentenceCase(true) == 'Turn To Sentence Case'
		where:
			input << [
				'turn-to-sentence-case',
				'TurnToSentenceCase',
				'Turn to sentence case',
				'Turn To Sentence Case'
			]
	}
}
