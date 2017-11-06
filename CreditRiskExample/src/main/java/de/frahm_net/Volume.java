package de.frahm_net;

public class Volume {
	private int amount;

	public Volume(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isAtLeast(Volume v) {
		return this.amount >= v.getAmount();
	}

	public boolean isBelow(Volume v) {
		return this.amount < v.getAmount();
	}
}
