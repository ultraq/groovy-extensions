/* 
 * Copyright 2020, Emanuel Rabina (http://www.ultraq.net.nz/)
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

package nz.net.ultraq.extensions

import spock.lang.Specification

import java.util.concurrent.ExecutorService

/**
 * Tests for the extension methods to {@code ExecutorService} instances.
 * 
 * @author Emanuel Rabina
 */
class ExecutorServiceExtensionsTests extends Specification {

	def "Invokes the closure, passing itself as a parameter, before shutting down"() {
		given:
			def executorService = Mock(ExecutorService)
			def closureParam = null
		when:
			executorService.executeAndShutdown {
				closureParam = it
			}
		then:
			closureParam == executorService
			1 * executorService.shutdown()
	}
}
