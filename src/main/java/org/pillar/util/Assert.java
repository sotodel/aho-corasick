/*
 * Copyright 2014 Sotirios Delimanolis
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
package org.pillar.util;

import java.util.Collection;

/**
 * A utility class for validating arguments.
 * 
 * @author Sotirios Delimanolis
 * @since 0.0.1
 */
public final class Assert {
	/**
	 * Throws an IllegalArgumentException if the given Collection is empty.
	 * 
	 * @param collection a Collection
	 */
	public static void isNotEmpty(Collection<?> collection) {
		isNotEmpty(collection, "collection cannot be empty");
	}

	/**
	 * Throws an IllegalArgumentException with the given message if the given 
	 * Collection is empty.
	 * 
	 * @param collection a Collection
	 */
	public static void isNotEmpty(Collection<?> collection, String message) {
		if (collection.isEmpty()) {
			throw new IllegalArgumentException(message);
		}		
	}
}
