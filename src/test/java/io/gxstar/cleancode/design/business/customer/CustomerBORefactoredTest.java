package io.gxstar.cleancode.design.business.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.gxstar.cleancode.design.business.exception.DifferentCurrenciesException;
import io.gxstar.cleancode.design.model.customer.Amount;
import io.gxstar.cleancode.design.model.customer.AmountImpl;
import io.gxstar.cleancode.design.model.customer.Currency;
import io.gxstar.cleancode.design.model.customer.Product;
import io.gxstar.cleancode.design.model.customer.ProductImpl;
import io.gxstar.cleancode.design.model.customer.ProductType;

public class CustomerBORefactoredTest {

	private CustomerBO customerBO = new CustomerBOImpl();

	@Test
	public void testCustomerProductSum_TwoProductsSameCurrencies()
			throws DifferentCurrenciesException {

		Amount[] amounts = {
				new AmountImpl(new BigDecimal("5.0"), Currency.EURO), 
				new AmountImpl(new BigDecimal("6.0"), Currency.EURO) };
		
		Amount expected = 
				new AmountImpl(new BigDecimal("11.0"), Currency.EURO);
		
		List<Product> products = createProductsWithAmounts(amounts);

		assertAmount(expected, customerBO.getCustomerProductsSum(products));
	}
	
	@Test
	public void testCustomerProductSum_TwoProductsDifferentCurrencies()
			throws DifferentCurrenciesException {

		Amount[] amounts = {
				new AmountImpl(new BigDecimal("5.0"), Currency.EURO), 
				new AmountImpl(new BigDecimal("6.0"), Currency.INDIAN_RUPEE) };
				
		List<Product> products = createProductsWithAmounts(amounts);
		
		Assertions.assertThrows(DifferentCurrenciesException.class, ()->{
			customerBO.getCustomerProductsSum(products);
		});
		
	}


	@Test
	public void testCustomerProductSum_EmptyProducts() throws DifferentCurrenciesException {

		Amount actual = customerBO.getCustomerProductsSum(
				new ArrayList<Product>());
		
		Amount expected = new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		assertAmount(expected, actual);
	}

	private void assertAmount(Amount expected, Amount actual) {
		assertEquals(expected.getCurrency(), actual.getCurrency());
		assertEquals(expected.getValue(), actual.getValue());
	}

	private List<Product> createProductsWithAmounts(Amount[] amounts) {
		
		return Arrays.stream(amounts)
				.map(amount -> 
					new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE,
							amount))
				.collect(Collectors.toList());
		
//		List<Product> products = new ArrayList<Product>();
//		
//		for(Amount amount:amounts) {
//			products.add(
//					new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE,
//							amount));
//			
//		}
//
//		return products;
	}

}