package io.gxstar.cleancode.design.model.customer;

/**
 * Collateral Model API.
 */
public interface Collateral {

	long getId();

	String getName();

	CollateralType getType();
}
