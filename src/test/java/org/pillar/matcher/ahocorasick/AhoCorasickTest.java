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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.pillar.matcher.MatchResult;

public class AhoCorasickTest {

	@Test
	public void match() {
		final Set<String> keywords = new LinkedHashSet<>();
		keywords.add("he");
		keywords.add("she");
		keywords.add("his");
		keywords.add("hers");
		
		final AhoCorasick ahoCorasick = new AhoCorasick(keywords);
	
		assertEquals(keywords, ahoCorasick.getKeywords());
		
		String text = "These are the words she spoke. What's his is hers.";
		             
		assertTrue(ahoCorasick.containsMatch(text));
		
		List<MatchResult> matchResults = ahoCorasick.match(text);
		MatchResult matchResult = matchResults.get(0);
		assertEquals(matchResult.matchedString(), "he");
		assertEquals(matchResult.start(), 1);
		assertEquals(matchResult.end(), 3);
		
		matchResult = matchResults.get(1);		
		assertEquals(matchResult.matchedString(), "he");
		assertEquals(matchResult.start(), 11);
		assertEquals(matchResult.end(), 13);
		
		matchResult = matchResults.get(2);		
		assertEquals(matchResult.matchedString(), "she");
		assertEquals(matchResult.start(), 20);
		assertEquals(matchResult.end(), 23);
		
		matchResult = matchResults.get(3);
		assertEquals(matchResult.matchedString(), "he");
		assertEquals(matchResult.start(), 21);
		assertEquals(matchResult.end(), 23);
		
		matchResult = matchResults.get(4);
		assertEquals(matchResult.matchedString(), "his");
		assertEquals(matchResult.start(), 38);
		assertEquals(matchResult.end(), 41);
		
		matchResult = matchResults.get(5);
		assertEquals(matchResult.matchedString(), "he");
		assertEquals(matchResult.start(), 45);
		assertEquals(matchResult.end(), 47);
		
		matchResult = matchResults.get(6);
		assertEquals(matchResult.matchedString(), "hers");
		assertEquals(matchResult.start(), 45);
		assertEquals(matchResult.end(), 49);
		
		assertThat(matchResults.size(), is(7));
	}
}