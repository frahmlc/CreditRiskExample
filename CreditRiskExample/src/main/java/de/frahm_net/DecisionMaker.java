package de.frahm_net;

import java.util.ArrayList;

/**
 * Using decision rules, the DecisionMaker class can help make decisions about
 * credit risk. Based on the rules defined using addRule, the decide method
 * makes a decision to approve or deny a credit transaction. If there is not
 * enough information about the customer, additional security is needed.
 * 
 * @author Lars Frahm
 *
 */
public class DecisionMaker {

	ArrayList<Rule> rules;

	public DecisionMaker() {
		rules = new ArrayList<Rule>();
		addRule(Rule.ratingNeededAboveLimit(Rating.C, new Volume(1)));
	}
	/**
	 * Suggest a decision for a credit transaction based on the information about 
	 * the customer and the credit volume.
	 * 
	 * @param customer
	 * @param volume
	 * @return decision
	 */
	public Decision decide(Customer customer, Volume volume) {

		if (customer.getRating() == Rating.UNKNOWN) {
			return Decision.NEED_SECURITY;
		}

		if (customer.getRating().isAtLeast(minimalRatingNeededFor(volume))) {
			return Decision.APPROVE;
		}

		return Decision.DENY;
	}

	private Rating minimalRatingNeededFor(Volume volume) {
		// Find the rule with the closest limit below the volume and return the minimal
		// rating needed.
		Volume closestLimit = new Volume(0);
		Rating minimalRatingNeeded = Rating.D;
		for (Rule rule : rules) {
			Volume ruleLimit = rule.getLimit();
			if (ruleLimit.isBelow(volume) && ruleLimit.isAtLeast(closestLimit)) {
				closestLimit = ruleLimit;
				minimalRatingNeeded = rule.getRatingNeeded();
			}
		}
		return minimalRatingNeeded;
	}

	public void addRule(Rule businessRule) {
		rules.add(businessRule);
	}

}
