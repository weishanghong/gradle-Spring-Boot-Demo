package com.weiresearch.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "general")
@Component
@PropertySource("resources.properties")
public class ResourcesProperties {

	private String pdUrl;

	public String getPdUrl() {
		return pdUrl;
	}
}
