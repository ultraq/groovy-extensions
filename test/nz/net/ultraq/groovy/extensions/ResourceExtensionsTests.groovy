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

/**
 * Tests for the {@link ResourceExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class ResourceExtensionsTests {

	@Test
	void '#getResourceAsFile - Is a shortcut to new File(ClassLoader#getResource()#toURI())'() {
		given:
			var resourcePath = 'nz/net/ultraq/groovy/extensions/ResourceExtensionsTestsFile.txt'
		when:
			var result = getResourceAsFile(resourcePath)
		then:
			assertThat(result.text).isEqualTo(new File(this.class.classLoader.getResource(resourcePath).toURI()).text)
	}

	@Test
	void '#getResourceAsFile - Throws an exception if the path leads to nothing'() {
		given:
			var resourcePath = 'path/to/nothing'
		when:
			var exception = catchException { -> getResourceAsFile(resourcePath) }
		then:
			assertThat(exception).isInstanceOf(IllegalArgumentException)
			assertThat(exception).hasMessage("Resource not found: ${resourcePath}")
	}

	@Test
	void "#getResourceAsStream - Is a shortcut to ClassLoader#getResourceAsStream"() {
		given:
			var resourcePath = 'nz/net/ultraq/groovy/extensions/ResourceExtensionsTestsFile.txt'
		when:
			var result = getResourceAsStream(resourcePath)
		then:
			assertThat(result.text).isEqualTo(this.class.classLoader.getResourceAsStream(resourcePath).text)
	}

	@Test
	void '#getResourceAsStream - Throws an exception if the path leads to nothing'() {
		given:
			var resourcePath = 'path/to/nothing'
		when:
			var exception = catchException { -> getResourceAsStream(resourcePath) }
		then:
			assertThat(exception).isInstanceOf(IllegalArgumentException)
			assertThat(exception).hasMessage("Resource not found: ${resourcePath}")
	}

	@Test
	void '#getResourceAsText - Returns the text content of a resource'() {
		given:
			var resourcePath = 'nz/net/ultraq/groovy/extensions/ResourceExtensionsTestsFile.txt'
		when:
			var result = getResourceAsText(resourcePath)
		then:
			assertThat(result).isEqualTo(getResourceAsStream(resourcePath).withCloseable { it.text })
	}
}
