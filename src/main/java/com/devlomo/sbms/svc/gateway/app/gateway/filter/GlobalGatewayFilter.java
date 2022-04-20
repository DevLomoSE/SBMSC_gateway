package com.devlomo.sbms.svc.gateway.app.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalGatewayFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.warn("excecuting the prefilter");
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			log.warn("excecuting the postfilter");
			exchange.getResponse()
					.getCookies()
					.add("env", ResponseCookie.from("env", "dev")
							.build());
			exchange.getResponse()
					.getHeaders()
					.setContentType(MediaType.TEXT_PLAIN);	
		}));
	}

}
