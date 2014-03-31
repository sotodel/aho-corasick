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

package org.pillar.dfa;

/**
 * An interface describing the behaviour of a State in a deterministic finite
 * automata (DFA).
 * 
 * @author Sotirios Delimanolis
 *
 * @param <StateType> The type of the State
 * @param <TransitionType> The type of value that triggers a transition to another State
 * @param <ValueType> The type of value held at each State
 */
public interface State<StateType extends State<StateType, TransitionType, ValueType>, TransitionType, ValueType> {
	/**
	 * Triggers a failure. Returns the State to which the deterministic finite
	 * automata should move to when a failure on this State occurs.
	 * 
	 * @return the State the DFA should be in after this failure
	 */
	public StateType fail();

	/**
	 * Triggers a transition. Returns the State to which the deterministic finite 
	 * automata should move to after consuming the input symbol.
	 * 
	 * @param transitionValue the DFA input symbol that triggers the transition
	 * @return the State the DFA should be in after consuming the input symbol
	 */
	public StateType go(TransitionType transitionValue);

	/**
	 * Returns the value stored at this State
	 * 
	 * @return the value stored at this State
	 */
	public ValueType value();
}
