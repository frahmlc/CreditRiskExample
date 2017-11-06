package de.frahm_net;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.frahm_net.Customer;
import de.frahm_net.Decision;
import de.frahm_net.DecisionMaker;
import de.frahm_net.Rating;
import de.frahm_net.Rule;
import de.frahm_net.Volume;

public class DecisionMakerTest {

	DecisionMaker d;

	@Before
	public void init() {
		d = new DecisionMaker();
	}

	@Test
	public void testApproveZeroVolume() {
		assertEquals(Decision.APPROVE, d.decide(new Customer(Rating.D), new Volume(0)));
	}

	@Test
	public void testDenyCustomerWithCreditRatingD() {
		assertEquals(Decision.DENY, d.decide(new Customer(Rating.D), new Volume(1000)));
	}

	@Test
	public void testCustomerWithoutRating() {
		assertEquals(Decision.NEED_SECURITY, d.decide(new Customer(Rating.UNKNOWN), new Volume(1000)));
	}

	@Test
	public void testBusinessRule() {
		d.addRule(Rule.ratingNeededAboveLimit(Rating.B, new Volume(1000)));

		Customer ratedHighEnough = new Customer(Rating.B);
		Customer ratedTooLow = new Customer(Rating.CCC);

		Volume aboveLimit = new Volume(1001);
		Volume belowLimit = new Volume(999);

		assertEquals(Decision.APPROVE, d.decide(ratedHighEnough, aboveLimit));
		assertEquals(Decision.APPROVE, d.decide(ratedHighEnough, belowLimit));
		assertEquals(Decision.APPROVE, d.decide(ratedTooLow, belowLimit));
		assertEquals(Decision.DENY, d.decide(ratedTooLow, aboveLimit));
	}

	@Test
	public void testAdditionalRule() {
		d.addRule(Rule.ratingNeededAboveLimit(Rating.B, new Volume(1000)));
		d.addRule(Rule.ratingNeededAboveLimit(Rating.A, new Volume(10000)));

		Volume aboveHigherLimit = new Volume(10001);
		Volume inBetweenLimits = new Volume(1001);
		Volume belowLowerLimit = new Volume(999);

		Customer ratedHighest = new Customer(Rating.A);
		assertEquals(Decision.APPROVE, d.decide(ratedHighest, aboveHigherLimit));
		assertEquals(Decision.APPROVE, d.decide(ratedHighest, inBetweenLimits));
		assertEquals(Decision.APPROVE, d.decide(ratedHighest, belowLowerLimit));

		Customer ratedInBetween = new Customer(Rating.BB);
		assertEquals(Decision.DENY, d.decide(ratedInBetween, aboveHigherLimit));
		assertEquals(Decision.APPROVE, d.decide(ratedInBetween, inBetweenLimits));
		assertEquals(Decision.APPROVE, d.decide(ratedInBetween, belowLowerLimit));

		Customer ratedTooLow = new Customer(Rating.C);
		assertEquals(Decision.DENY, d.decide(ratedTooLow, aboveHigherLimit));
		assertEquals(Decision.DENY, d.decide(ratedTooLow, inBetweenLimits));
		assertEquals(Decision.APPROVE, d.decide(ratedTooLow, belowLowerLimit));
	}

}
