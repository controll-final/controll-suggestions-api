package com.hiringcoders.controll.core.vtex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hiringcoders.controll.infrastructure.vtex.clients.VtexApiClient;

@Configuration
public class VtexApiClientConfig {

	@Autowired
	private VtexApiProperties properties;
	
	@Bean
	public VtexApiClient vtexApiClient() {
		return VtexApiClient.builder()
				.vtexAccountName(properties.getVtexAccountName())
				.vtexEnvironment(properties.getVtexEnvironment())
				.vtexAppKey(properties.getVtexAppKey())
				.vtexAppToken(properties.getVtexAppToken())
				.build();
	}

}
