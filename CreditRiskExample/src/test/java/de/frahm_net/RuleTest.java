package de.frahm_net;

import static org.junit.Assert.*;

import org.junit.Test;

import de.frahm_net.Rating;
import de.frahm_net.Rule;
import de.frahm_net.Volume;

public class RuleTest {

	@Test
	public void testConstruction() {
		Rule r = Rule.ratingNeededAboveLimit(Rating.AAA, new Volume(1000));
		assertEquals(Rating.AAA, r.getRatingNeeded());
		assertEquals(1000, r.getLimit().getAmount());
	}

}
