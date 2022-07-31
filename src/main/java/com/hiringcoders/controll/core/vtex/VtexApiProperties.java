package com.hiringcoders.controll.core.vtex;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("vtex.api")
public class VtexApiProperties {
	
	private String vtexAccountName;
	
	private String vtexEnvironment;
	
	private String vtexAppKey;
	
	private String vtexAppToken;

}