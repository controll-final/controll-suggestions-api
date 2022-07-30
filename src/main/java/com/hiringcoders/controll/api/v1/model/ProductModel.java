package com.hiringcoders.controll.api.v1.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {
	
	private Long id;
	
	private String name;
	
	private Boolean active;
	
	private BigDecimal quantitySold;

}