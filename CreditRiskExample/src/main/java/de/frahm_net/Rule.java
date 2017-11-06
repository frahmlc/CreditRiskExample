package de.frahm_net;

/**
 * Represents a business rule stating that a certain Rating is needed above a
 * given limit.
 * 
 * @author Lars Frahm
 *
 */
public class Rule {
	private Rating ratingNeeded;
	private Volume limit;

	private Rule(Rating ratingNeeded, Volume limit) {
		this.ratingNeeded = ratingNeeded;
		this.limit = limit;
	}

	public static Rule ratingNeededAboveLimit(Rating ratingNeeded, Volume limit) {
		return new Rule(ratingNeeded, limit);
	}

	public Rating getRatingNeeded() {
		return ratingNeeded;
	}

	public Volume getLimit() {
		return limit;
	}
}
