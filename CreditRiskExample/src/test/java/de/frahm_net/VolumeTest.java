package de.frahm_net;

import static org.junit.Assert.*;

import org.junit.Test;

import de.frahm_net.Volume;

public class VolumeTest {

	@Test
	public void testConstruction() {
		Volume v = new Volume(1000);
		assertEquals(1000, v.getAmount());
	}

	@Test
	public void testIsBelow() {
		Volume v1 = new Volume(1000);
		Volume v2 = new Volume(1001);
		assertTrue(v1.isBelow(v2));
		assertFalse(v2.isBelow(v1));
	}

	@Test
	public void testIsAtLeast() {
		Volume v1 = new Volume(1000);
		Volume v2 = new Volume(1001);
		assertTrue(v2.isAtLeast(v1));
		assertTrue(v1.isAtLeast(v1));
		assertFalse(v1.isAtLeast(v2));
	}

}
