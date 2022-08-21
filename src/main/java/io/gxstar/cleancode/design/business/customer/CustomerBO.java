package io.gxstar.cleancode.design.business.customer;

import java.util.List;

import io.gxstar.cleancode.design.business.exception.DifferentCurrenciesException;
import io.gxstar.cleancode.design.model.customer.Amount;
import io.gxstar.cleancode.design.model.customer.Product;

public interface CustomerBO {

	Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException;

}