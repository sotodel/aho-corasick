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

package org.pillar.matcher.ahocorasick;

import org.pillar.matcher.MatchResult;

/**
 * A MatchResult for a match of the Aho-Corasick string matching algorithm.
 * 
 * @author Sotirios Delimanolis
 * @since 0.0.1
 */
class AhoCorasickMatchResult implements MatchResult {
	// the matched string
	private final String matchedString;
	// the index of the first character matched
	private final int start;
	// the offset after the last character matched
	private final int end;

	AhoCorasickMatchResult(String matchedString, int start, int end) {
		this.matchedString = matchedString;
		this.end = end;
		this.start = start;
	}

	@Override
	public String matchedString() {
		return matchedString;
	}

	@Override
	public int start() {
		return start;
	}

	@Override
	public int end() {
		return end;
	}
	
	@Override
	public String toString() {
		return "[matchedString = '" + matchedString + "', start = " + start + ", end = " + end + "]";
	}
}
