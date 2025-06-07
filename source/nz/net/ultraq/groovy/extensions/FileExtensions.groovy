/*
 * Copyright 2024, Emanuel Rabina (http://www.ultraq.net.nz/)
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
 * Extensions to the {@link File} class.
 *
 * @author Emanuel Rabina
 */
class FileExtensions {

	/**
	 * Returns the file name, but without any extension part.
	 *
	 * @deprecated
	 *   Groovy 5 will include this as part of the GDK as {@code File.baseName} 🥳
	 * @param self
	 * @return
	 */
	@Deprecated
	static String getNameWithoutExtension(File self) {

		return self.name.contains('.') ? self.name.substring(0, self.name.indexOf('.')) : self.name
	}
}
