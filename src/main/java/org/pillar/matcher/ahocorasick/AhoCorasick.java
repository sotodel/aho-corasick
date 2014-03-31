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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import org.pillar.matcher.MatchResult;
import org.pillar.matcher.StringMatcher;
import org.pillar.util.Assert;

/**
 * A {@link StringMatcher} implemented with the 
 * <a href="http://en.wikipedia.org/wiki/Aho%E2%80%93Corasick_string_matching_algorithm">Aho-Corasick</a>
 * string matching state machine. 
 * 
 * @author Sotirios Delimanolis
 * @since 0.0.1
 */
public final class AhoCorasick implements StringMatcher {
	// the root state
	private final AhoCorasickState root;
	// the set of keywords and phrases
	private final Set<String> keywords;

	/**
	 * Initializes an Aho-Corasick state machine with the given keywords and phrases.
	 * 
	 * @param keywords a non-null, non-empty Set of keywords
	 */
	public AhoCorasick(Set<String> keywords) {
		Objects.requireNonNull(keywords, "keywords cannot be null.");
		Assert.isNotEmpty(keywords);
		
		this.root = new AhoCorasickState(0);
		this.keywords = keywords;
		constructGoto();
		constructFailure();
	}

	/*
	 * Constructs the goto function.
	 */
	private void constructGoto() {
		AhoCorasickState newState = root;
		for (String keyword : keywords) {
			this.enter(keyword, newState);
		}
		root.setDefaultValue(root);
	}

	/*
	 * Constructs each next state in the goto function.
	 */
	private void enter(String keyword, AhoCorasickState newState) {
		AhoCorasickState state = root;
		int j = 0;
		char character = keyword.charAt(j);

		AhoCorasickState next = root.go(character);
		while (next != AhoCorasickState.FAIL) {
			state = next;
			j++;
			character = keyword.charAt(j);
			next = next.go(character);
		}

		final int length = keyword.length();
		for (int p = j; p < length; p++) {
			character = keyword.charAt(p);

			newState = AhoCorasickState.next(newState);
			state.addGotoTransition(character, newState);
			state = newState;
		}
		state.addOutput(keyword);
	}

	/*
	 * Constructors the failure function.
	 */
	private void constructFailure() {
		Queue<AhoCorasickState> queue = new LinkedList<>();
		root.setFailureTransition(root);
		Collection<AhoCorasickState> zeroDepthStates = root.getGotoTransitions().values();
		for (AhoCorasickState zeroDepthState : zeroDepthStates) {
			queue.add(zeroDepthState);
			zeroDepthState.setFailureTransition(root);
		}

		AhoCorasickState state;
		while (!queue.isEmpty()) {
			AhoCorasickState r = queue.remove();
			for (Entry<Character, AhoCorasickState> transition : r.getGotoTransitions().entrySet()) {
				AhoCorasickState s = transition.getValue();
				Character character = transition.getKey();
				queue.add(s);
				state = r.fail();
				while (state.go(character) == AhoCorasickState.FAIL) {
					state = state.fail();
				}
				s.setFailureTransition(state.go(character));
				s.addAllOutputs(s.fail().value());
			}
		}
	}

	/*
	 * Helper method so that contains(String) doesn't have to wait for all matches.
	 */
	private List<MatchResult> match(String stringToMatch, boolean stopOnFirst) {
		final List<MatchResult> matchResults = new LinkedList<>();
		AhoCorasickState state = root;
		final int length = stringToMatch.length();
		
		stop: 
		for (int index = 0; index < length; index++) {
			char character = stringToMatch.charAt(index);
			
			while (state.go(character) == AhoCorasickState.FAIL) {
				state = state.fail();
			}
			
			state = state.go(character);
			
			if (!state.value().isEmpty()) {				
				for (String match : state.value()) {
					final int end = index + 1;
					MatchResult result = new AhoCorasickMatchResult(match, end - match.length(), end);
					matchResults.add(result);
					if (stopOnFirst) {
						break stop; 
					}
				}				
			}
		}
		return matchResults;
	}	
	
	@Override
	public List<MatchResult> match(String stringToMatch) {
		return match(stringToMatch, false);
	}

	@Override
	public boolean containsMatch(String stringToMatch) {
		return !match(stringToMatch, true).isEmpty();
	}
	
	/**
	 * Returns an unmodifiable view of the keywords.
	 * 
	 * @return an unmodifiable set of mapped keywords
	 */
	public Set<String> getKeywords() {
		return Collections.unmodifiableSet(keywords);
	}
}
