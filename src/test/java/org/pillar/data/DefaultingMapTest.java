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

import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertEquals;

public class DefaultingMapTest {
	@Test
	public void get() {
		final Integer defaultValue = new Integer(42);
		final String key = "key";
		
		DefaultingMap<String, Integer> map = new DefaultingMap<String, Integer>(new HashMap<>(), defaultValue);
		map.put(key, 0);
		Integer get = map.get(key);
		
		assertSame(defaultValue, map.get("random1234"));
		// integer cache
		assertSame(get, 0);
		// but also
		assertEquals(get, new Integer(0));
	}
}
