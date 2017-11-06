package de.frahm_net;

import static org.junit.Assert.*;

import org.junit.Test;

import de.frahm_net.Rating;

public class RatingTest {

	@Test
	public void testComparison() {
		assertTrue(Rating.A.isAtLeast(Rating.B));
		assertFalse(Rating.A.isAtLeast(Rating.AA));
	}

}
