package com.hiringcoders.controll.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.hiringcoders.controll.api.v1.model.ProductCombinationModel;
import com.hiringcoders.controll.api.v1.model.assembler.ProductCombinationModelAssembler;
import com.hiringcoders.controll.core.data.PageableTranslator;
import com.hiringcoders.controll.domain.model.Product;
import com.hiringcoders.controll.domain.model.ProductCombination;
import com.hiringcoders.controll.domain.repository.ProductCombinationRepository;
import com.hiringcoders.controll.domain.service.ProductCombinationRegistrationService;
import com.hiringcoders.controll.domain.service.ProductRegistrationService;

@RestController
@RequestMapping(path = "/v1/products/{productId}/combinations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductCombinationController {

	@Autowired
	private ProductCombinationRepository productCombinationRepository;

	@Autowired
	private ProductCombinationModelAssembler productCombinationModelAssembler;

	@Autowired
	private ProductRegistrationService productRegistration;
	
	@Autowired
	private ProductCombinationRegistrationService productCombinationRegistration;

	@GetMapping
	public Page<ProductCombinationModel> listCombinationsFromProduct(@PathVariable Long productId,
			@PageableDefault(size = 5) Pageable pageable) {
		Product product = productRegistration.findProductById(productId);
		
		pageable = translatePageable(pageable);

		Page<ProductCombination> productCombinationsPage = productCombinationRepository.findByProductId(product.getId(),
				pageable);

		List<ProductCombinationModel> productsCombinationModelList = productCombinationModelAssembler
				.toCollectionModel(productCombinationsPage.getContent());

		Page<ProductCombinationModel> productsModelPage = new PageImpl<>(productsCombinationModelList, pageable,
				productCombinationsPage.getTotalElements());

		return productsModelPage;
	}
	
	@PutMapping("/{combinedProductId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> activateCombination(@PathVariable Long productId, @PathVariable Long combinedProductId) {
		productCombinationRegistration.activateCombination(productId, combinedProductId);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{combinedProductId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disableCombination(@PathVariable Long productId, @PathVariable Long combinedProductId) {
		productCombinationRegistration.disableCombination(productId, combinedProductId);

		return ResponseEntity.noContent().build();
	}
	
	private Pageable translatePageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
				"productId", "product.id",
				"productName", "product.name",
				"productQuantitySold", "product.quantitySold",
				"combinedProductId", "combinedProduct.id",
				"combinedProductName", "combinedProduct.name",
				"combinedProductQuantitySold", "combinedProduct.quantitySold",
				"combinationCount", "count",
				"combinationActive", "active"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
