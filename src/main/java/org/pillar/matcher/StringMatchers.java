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

import java.util.Set;

import org.pillar.matcher.ahocorasick.AhoCorasick;

/**
 * Factory for {@link StringMatcher} classes.
 *  
 * @author Sotirios Delimanolis
 * @since 0.0.1
 */
public class StringMatchers {
	/**
	 * Creates a returns a new {@link AhoCorasick} instance for the given set of keywords.
	 * 
	 * @param keywords A non-null, non-empty Set of Strings
	 * @return an AhoCorasick instance
	 */
	public StringMatcher newAhoCorasick(Set<String> keywords) {
		return new AhoCorasick(keywords);
	}
}
