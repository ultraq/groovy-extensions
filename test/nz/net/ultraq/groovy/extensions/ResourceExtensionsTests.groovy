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

import spock.lang.Specification

/**
 * Tests for the {@link ResourceExtensions} methods.
 *
 * @author Emanuel Rabina
 */
class ResourceExtensionsTests extends Specification {

	String resourcePath = 'nz/net/ultraq/groovy/extensions/ResourceExtensionsTestsFile.txt'
	String noResourcePath = 'path/to/nothing'

	def "#getResourceAsBytes - Returns the bytes of a resource"() {
		expect:
			getResourceAsBytes(resourcePath) == this.class.classLoader.getResourceAsStream(resourcePath).bytes
	}

	def "#getResourceAsFile - Is a shortcut to new File(ClassLoader.getResource().toURI())"() {
		expect:
			getResourceAsFile(resourcePath).text == new File(this.class.classLoader.getResource(resourcePath).toURI()).text
	}

	def "#getResourceAsFile - Throws an exception if the path leads to nothing"() {
		when:
			getResourceAsFile(noResourcePath)
		then:
			var ex = thrown(IllegalArgumentException)
			assert ex.message == "Resource not found: ${noResourcePath}"
	}

	def "#getResourceAsStream - Is a shortcut to ClassLoader.getResourceAsStream"() {
		expect:
			getResourceAsStream(resourcePath).text == this.class.classLoader.getResourceAsStream(resourcePath).text
	}

	def "#getResourceAsStream - Throws an exception if the path leads to nothing"() {
		when:
			getResourceAsStream(noResourcePath)
		then:
			var ex = thrown(IllegalArgumentException)
			assert ex.message == "Resource not found: ${noResourcePath}"
	}

	def "#getResourceAsText - Returns the text content of a resource"() {
		expect:
			getResourceAsText(resourcePath) == getResourceAsStream(resourcePath).withCloseable { it.text }
	}
}
