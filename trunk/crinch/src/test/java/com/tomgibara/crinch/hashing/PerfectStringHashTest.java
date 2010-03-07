/*
 * Copyright 2010 Tom Gibara
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.tomgibara.crinch.hashing;

import com.tomgibara.crinch.hashing.PerfectStringHash;

import junit.framework.TestCase;

public class PerfectStringHashTest extends TestCase {

	public void testEmpty() {
		PerfectStringHash hash = new PerfectStringHash();
		assertTrue( hash.hash("X") < 0 );
	}

	public void testOne() {
		PerfectStringHash hash = new PerfectStringHash("a");
		assertEquals(0, hash.hash("a"));
		assertTrue( hash.hash("b") < 0 );
	}
	
	public void testAbsentClashes() {
		PerfectStringHash hash = new PerfectStringHash(new String[] {"a", "b"});
		assertEquals(0, hash.hash("a"));
		assertEquals(1, hash.hash("b"));
		assertTrue(hash.hash("c") < 0);
		
	}

	public void testSingleClash() {
		PerfectStringHash hash = new PerfectStringHash(new String[] {"Ab", "BC"});
		assertEquals(0, hash.hash("Ab"));
		assertEquals(1, hash.hash("BC"));
	}

	public void testClashCombos() {
		final String[] arr = new String[] {"Ab", "BC", "5u", "6V", "77"};
		PerfectStringHash hash = new PerfectStringHash(arr);
		for (int i = 0; i < arr.length; i++) {
			assertEquals(i, hash.hash(arr[i]));
		}
	}

	public void testClashCombosPlus() {
		final String[] arr = new String[] {"Ab", "BC", "5u", "6V", "77", "1", "A", "b", "C", "d", "11", "AA", "bb", "CC", "dd"};
		PerfectStringHash hash = new PerfectStringHash(arr);
		assertEquals(0, hash.hash("1"));
		assertEquals(14, hash.hash("dd"));
		
		for (int i = 0; i < arr.length; i++) {
			assertEquals(i, hash.hash(arr[i]));
		}
	}

	public void testDuplicates() {
		try {
			new PerfectStringHash("a", "b", "a");
			fail();
		} catch (IllegalArgumentException e) {
			//expected
		}
	}
	
}
