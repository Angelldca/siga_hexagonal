package com.angelldca.mcp_server;

import com.angelldca.mcp_server.service.WeatherService;
import com.angelldca.mcp_server.tool.EventTool;
import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.spec.McpSchema;
import org.apache.logging.log4j.util.BiConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;

import org.springframework.ai.tool.ToolCallbackProvider;

import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import java.util.List;


@SpringBootApplication
/*
* @EnableJpaRepositories(basePackages = "com.angelldca.siga.infrastructure.adapter.out.repository")
@EntityScan(basePackages = "com.angelldca.siga.infrastructure.adapter.out.persistence")
@ComponentScan(basePackages = {
		"com.angelldca.siga",
		"com.angelldca.mcp_server"
})
* */

public class McpServerApplication {
	private static final Logger logger = LoggerFactory.getLogger(McpServerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(McpServerApplication.class, args);
	}

	@Bean
	public List<ToolCallback> danTools(EventTool eventTool) {
		return List.of(ToolCallbacks.from(eventTool));
	}

}

