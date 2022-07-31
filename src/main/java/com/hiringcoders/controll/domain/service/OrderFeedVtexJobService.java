package com.hiringcoders.controll.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiringcoders.controll.infrastructure.vtex.clients.VtexApiClient;
import com.hiringcoders.controll.infrastructure.vtex.model.FeedOrderVtex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderFeedVtexJobService {

	private VtexApiClient vtexApi;
	
	private OrderVtexHandlerService orderHandler;

	@Autowired
	public OrderFeedVtexJobService(VtexApiClient vtexApi, OrderVtexHandlerService orderHandler) {
		this.vtexApi = vtexApi;
		this.orderHandler = orderHandler;
	}

	public void doJob() {
		log.info("Executando Consulta no Feed");
		List<FeedOrderVtex> feedItems = vtexApi.getFeedOrder();
		
		log.info("Foram encontrados " + feedItems.size() + " elementos no feed");
		
		for (FeedOrderVtex feedItem : feedItems) {
			orderHandler.handleOrder(feedItem.getOrderId());
			vtexApi.commitItemFeed(feedItem.getHandle());
		}
	}

}
