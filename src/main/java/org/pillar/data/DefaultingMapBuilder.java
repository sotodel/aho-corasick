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

package org.pillar.data;

import java.util.HashMap;
import java.util.Map;

public class DefaultingMapBuilder<K, V> {
	
	public static <K, V> DefaultingMapBuilder<K, V> create() {
		return new DefaultingMapBuilder<>();
	}
	
	private Map<K, V> targetInnerMap;
	private V defaultValue;
	
	public DefaultingMapBuilder<K, V> withHashMap() {
		targetInnerMap = new HashMap<>();
		return this;
	}
	
	public DefaultingMapBuilder<K, V> withDefaultValue(V defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}
	
	public DefaultingMap<K, V> build() {
		return new DefaultingMap<K, V>(targetInnerMap, defaultValue);
	}
}
