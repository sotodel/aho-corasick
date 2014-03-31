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

import java.util.List;

/**
 * An interface for string matchers. 
 * @author Sotirios Delimanolis
 * @since 0.0.1
 */
public interface StringMatcher {
	/**
	 * Matches the given String and returns a List of match results.
	 *  
	 * @param stringToMatch the String to match
	 * @return a List of match results
	 */
	List<MatchResult> match(String stringToMatch);
	
	/**
	 * Returns if the given String has any matches.
	 * 
	 * @param stringToMatch the String to match
	 * @return true if this StringMatcher matches the given String, false otherwise
	 */
	boolean containsMatch(String stringToMatch);
}
