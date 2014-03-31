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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.pillar.dfa.State;

/**
 * A State in the Aho-Corasick finite state machine. The input symbols are of 
 * type Character and each AhoCorasickState holds a Collection of String values.
 * 
 * @author Sotirios Delimanolis
 * @since 0.0.1
 */
class AhoCorasickState implements State<AhoCorasickState, Character, Collection<String>> {
	// the State representing fail in the Aho-Corasick goto function
	static final AhoCorasickState FAIL = new AhoCorasickState(-1);

	// the state index
	private final int state;
	// a Map of the transitions for the goto function
	// each Character input symbol maps to a AhoCorasickState
	private final Map<Character, AhoCorasickState> gotoTransitions = new HashMap<>();
	// the target AhoCorasickState for a failure transition 
	private AhoCorasickState failureTransition;
	// the state value
	private final Collection<String> outputs = new HashSet<>();
	// the default value that the goto transition should return if no transition is possible
	private AhoCorasickState defaultValue = FAIL;

	AhoCorasickState(int state) {
		this.state = state;
	}

	/**
	 * Sets the default value for a failed goto transition.
	 * 
	 * @param defaultValue the default value
	 */
	void setDefaultValue(AhoCorasickState defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Adds a transition to the specified AhoCorasickState for the given Character
	 * input symbol.
	 * 
	 * @param character the input symbol
	 * @param targetState the target state
	 */
	void addGotoTransition(Character character,	AhoCorasickState targetState) {
		gotoTransitions.put(character, targetState);
	}

	/**
	 * Sets the target state for a failure transition.
	 * 
	 * @param target the target state
	 */
	void setFailureTransition(AhoCorasickState target) {
		failureTransition = target;
	}

	/**
	 * Adds a value to this state.
	 * 
	 * @param output the value to add
	 */
	void addOutput(String output) {
		outputs.add(output);
	}

	/**
	 * Adds all the values in the given Collection to this state.
	 * 
	 * @param outputs the values to add
	 */
	void addAllOutputs(Collection<String> values) {
		outputs.addAll(values);
	}
	
	/**
	 * Returns the Aho-Corasick goto transitions for this state in an unmodifiable
	 * map.
	 * 
	 * @return an unmodifiable map of the goto transitions
	 */
	Map<Character, AhoCorasickState> getGotoTransitions() {
		return Collections.unmodifiableMap(gotoTransitions);
	}
	
	@Override
	public AhoCorasickState fail() {
		return failureTransition;
	}

	@Override
	public AhoCorasickState go(Character character) {
		AhoCorasickState target = gotoTransitions.get(character);
		return (target == null) ? defaultValue : target;
	}

	/**
	 * Returns the Collection of String values stored at this state.
	 * 
	 * @return an unmodifiable collection of the strings stored at this state
	 */
	@Override
	public Collection<String> value() {
		return Collections.unmodifiableCollection(outputs);
	}

	/**
	 * A helper function for creating AhoCorasickState instance from the given AhoCorasickState.
	 * 
	 * Returns a new AhoCorasickState with an index value of one more than the index of the given
	 * AhoCorasickState.
	 * 
	 * @param newState the new AhoCorasickState
	 * @return
	 */
	static AhoCorasickState next(AhoCorasickState newState) {
		return new AhoCorasickState(newState.state + 1);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((defaultValue == null) ? 0 : defaultValue.hashCode());
		result = prime * result	+ ((failureTransition == null) ? 0 : failureTransition.hashCode());
		result = prime * result	+ ((gotoTransitions == null) ? 0 : gotoTransitions.hashCode());
		result = prime * result + ((outputs == null) ? 0 : outputs.hashCode());
		result = prime * result + state;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AhoCorasickState other = (AhoCorasickState) obj;
		if (defaultValue == null) {
			if (other.defaultValue != null)
				return false;
		} else if (!defaultValue.equals(other.defaultValue))
			return false;
		if (failureTransition == null) {
			if (other.failureTransition != null)
				return false;
		} else if (!failureTransition.equals(other.failureTransition))
			return false;
		if (gotoTransitions == null) {
			if (other.gotoTransitions != null)
				return false;
		} else if (!gotoTransitions.equals(other.gotoTransitions))
			return false;
		if (outputs == null) {
			if (other.outputs != null)
				return false;
		} else if (!outputs.equals(other.outputs))
			return false;
		if (state != other.state)
			return false;
		return true;
	}
}
