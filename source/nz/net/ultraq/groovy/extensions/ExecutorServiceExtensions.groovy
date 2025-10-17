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

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

/**
 * Extensions to the {@code ExecutorService} interface.
 *
 * @author Emanuel Rabina
 */
class ExecutorServiceExtensions {

	private static Logger logger = LoggerFactory.getLogger(ExecutorServiceExtensions)

	/**
	 * Execute the given closure, performing a shutdown after it has exited (see
	 * {@link #shutdownAwaitTermination(ExecutorService, int, TimeUnit)}).
	 * <pre>
	 *   var executorService = Executors.newCachedThreadPool()
	 *   executorService.executeAndShutdown { e ->
	 *     // Use the executorService within this closure
	 *   }
	 *   assert executorService.shutdown
	 * </pre>
	 * <p>Note that Java 19 made {@link ExecutorService} implement {@link AutoCloseable}
	 * and the {@code close} method basically does what this method does, but with
	 * a default await value of 1 day (!!!).  Still, if tasks are taking any
	 * period of time after shutdown then there might be something worth
	 * investigating.
	 *
	 * @param <T>
	 * @param self
	 * @param awaitValue
	 * @param awaitUnit
	 * @param closure
	 *   Called within the context of a try/finally block with the executor
	 *   service itself for performing any parallel tasks.
	 * @return The return value from the closure.
	 */
	static <T> T executeAndShutdown(ExecutorService self, int awaitValue = 5, TimeUnit awaitUnit = TimeUnit.SECONDS,
		@ClosureParams(value = SimpleType, options = 'java.util.concurrent.ExecutorService')
		Closure<T> closure) {

		try {
			return closure(self)
		}
		finally {
			self.shutdownAwaitTermination(awaitValue, awaitUnit)
		}
	}

	/**
	 * Initiate a shutdown, waiting the specified amount of time before forcing
	 * termination.  A default value of 5 seconds is used if neither await value
	 * is specified.
	 *
	 * @param self
	 * @param awaitValue
	 * @param awaitUnit
	 */
	static void shutdownAwaitTermination(ExecutorService self, int awaitValue = 5, TimeUnit awaitUnit = TimeUnit.SECONDS) {

		self.shutdown()
		try {
			if (!self.awaitTermination(awaitValue, awaitUnit)) {
				var tasks = self.shutdownNow()
				if (tasks) {
					logger.warn('ExecutorService was foricbly terminated, but {} tasks did not respond', tasks.size())
				}
			}
		}
		catch (InterruptedException ignored) {
			self.shutdownNow()
			Thread.currentThread().interrupt()
		}
	}
}
