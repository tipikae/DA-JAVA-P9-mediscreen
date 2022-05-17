/**
 * 
 */
package com.tipikae.mediscreenproxy.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.netty.handler.logging.LogLevel;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

/**
 * Logging requests and responses.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class FiltersForLogging implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(FiltersForLogging.class);
    
    @Bean
    HttpClient httpClient() {
        return HttpClient.create().wiretap("LoggingFilter", LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	LOGGER.info("Pre-handle request: " + exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
        	  LOGGER.info("Post-handle response: " + exchange.getResponse().getStatusCode());
            }));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
