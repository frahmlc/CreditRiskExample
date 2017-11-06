package de.frahm_net;

/**
 * Credit rating, a comparison function is implemented for convenience.
 *
 * @author Lars Frahm
 *
 */
public enum Rating {
	UNKNOWN, D, C, CC, CCC, B, BB, BBB, A, AA, AAA;

	public Boolean isAtLeast(Rating r) {
		return (this.ordinal() >= r.ordinal());
	}
}
