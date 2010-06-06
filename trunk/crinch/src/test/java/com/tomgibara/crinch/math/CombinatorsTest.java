package com.tomgibara.crinch.math;

import static com.tomgibara.crinch.math.Combinators.chooseAsLong;
import static java.math.BigInteger.valueOf;
import junit.framework.TestCase;

public class CombinatorsTest extends TestCase {

	public void testChooseAsLong() {
		assertEquals(1, chooseAsLong(0, 0));
		assertEquals(1, chooseAsLong(1, 1));
		assertEquals(1, chooseAsLong(1, 0));
		assertEquals(1, chooseAsLong(2, 0));
		assertEquals(2, chooseAsLong(2, 1));
		assertEquals(1, chooseAsLong(2, 2));
		assertEquals(10 * 9 * 8 * 7 / 4 / 3 / 2 / 1, Combinators.chooseAsLong(10, 4));
	}
	
	public void testSymmetry() {
		for (int n = 0; n < 20; n++) {
			for (int k = 0; k <= n; k++) {
				assertEquals(chooseAsLong(n, k), chooseAsLong(n, n - k));
			}
		}
	}

	public void testConsistency() {
		for (int n = 0; n < 20; n++) {
			for (int k = 0; k <= n; k++) {
				assertEquals(valueOf(chooseAsLong(n, k)), Combinators.chooseAsBigInt(n, k));
			}
		}
	}

}
