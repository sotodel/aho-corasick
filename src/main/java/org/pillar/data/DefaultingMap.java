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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A {@link Map} wrapper meant to return a default value in case a given key is
 * not mapped.
 * 
 * @since 0.0.1
 * @author Sotirios Delimanolis
 * @param <K>
 *            the type of keys maintained by this map
 * @param <V>
 *            the type of mapped values
 */
public class DefaultingMap<K, V> implements Map<K, V> {

	private final Map<K, V> map;
	private V defaultValue;

	public DefaultingMap(Map<K, V> map, V defaultValue) {
		this.map = map;
		this.defaultValue = defaultValue;
	}
	
	public void setDefaultValue(V defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	@Override
	public V get(Object key) {
		V ret = map.get(key);
		if (ret == null) {
			ret = defaultValue;
		}
		return ret;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public V put(K key, V value) {
		return map.put(key, value);
	}

	@Override
	public V remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		this.map.putAll(map);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<V> values() {
		return map.values();
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return map.entrySet();
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		return map.equals(object);
	}

	@Override
	public String toString() {
		return map.toString();
	}
}
