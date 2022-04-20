package com.devlomo.sbms.svc.gateway.app.gateway.filter;

import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalGatewayFilter implements GlobalFilter, Ordered {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.warn("excecuting the prefilter");
		
		exchange.getRequest().mutate().headers(h -> h.add("token", "123456"));
		
		
		// before the return all process is on the prefilter
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			log.warn("excecuting the postfilter");
			
			Optional.ofNullable(exchange.getRequest()
										.getHeaders()
										.getFirst("token"))
					.ifPresent(v -> {
				exchange.getResponse().getHeaders().add("token", "123456");
			});
			
			exchange.getResponse()
					.getCookies()
					.add("env", ResponseCookie.from("env", "dev")
							.build());
			/*exchange.getResponse()
					.getHeaders()
					.setContentType(MediaType.TEXT_PLAIN);*/	
		}));
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
