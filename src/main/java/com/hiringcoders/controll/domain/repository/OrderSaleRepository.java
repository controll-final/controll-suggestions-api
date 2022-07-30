package com.hiringcoders.controll.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hiringcoders.controll.domain.model.OrderSale;

@Repository
public interface OrderSaleRepository extends JpaRepository<OrderSale, String>, JpaSpecificationExecutor<OrderSale> {

}