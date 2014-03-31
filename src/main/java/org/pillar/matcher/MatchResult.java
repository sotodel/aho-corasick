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

package org.pillar.matcher;

/**
 * An interface representing a string matching result.
 *  
 * @author Sotirios Delimanolis
 * @since 0.0.1
 */
public interface MatchResult {
	/**
	 * Returns the result of the string matching operation.
	 * 
	 * @return the matched string
	 */
	String matchedString();
	
	/**
	 * Returns the index of the first character matched
	 * 
	 * @return the index of the first character matched
	 */
	int start();
	
	/**
	 * Returns the offset after the last character matched.
	 * 
	 * @return The offset after the last character matched
	 */
	int end();
}
