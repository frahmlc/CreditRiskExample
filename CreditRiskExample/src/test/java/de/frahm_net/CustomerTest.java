package de.frahm_net;

import static org.junit.Assert.*;

import org.junit.Test;

import de.frahm_net.Customer;
import de.frahm_net.Rating;

public class CustomerTest {

	@Test
	public void testConstruction() {
		Customer customer = new Customer(Rating.AAA);
		assertEquals(Rating.AAA, customer.getRating());
	}

}
