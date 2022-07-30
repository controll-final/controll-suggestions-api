package com.hiringcoders.controll.api.v1.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCombinationModel {
	
	private Long productId;
	
	private String productName;
	
	private BigDecimal productQuantitySold;
	
	private Long combinedProductId;
	
	private String combinedProductName;
	
	private BigDecimal combinedProductQuantitySold;
	
	private Long combinationCount;
	
	private Boolean combinationActive;

}
