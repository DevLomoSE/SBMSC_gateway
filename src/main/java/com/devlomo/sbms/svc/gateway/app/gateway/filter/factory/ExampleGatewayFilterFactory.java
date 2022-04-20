package com.devlomo.sbms.svc.gateway.app.gateway.filter.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class ExampleGatewayFilterFactory extends AbstractGatewayFilterFactory<Configuration> {

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("msg","cookieName", "cookieValue");
	}

	@Override
	public String name() {
		return "ProductsC";
	}

	public ExampleGatewayFilterFactory() {
		super(Configuration.class);
	}

	@Override
	public GatewayFilter apply(Configuration config) {
		return (exchange, chain) -> {
			// prefilter
			log.info("excecuting prefilter in gatewayFilterFactory: {}", config.toString());

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				// postfilter
				log.info("excecuting postfilter in gatewayFilterFactory");

				Optional.ofNullable(config.getCookieValue()).ifPresent(c -> {
					exchange.getResponse()
							.addCookie(ResponseCookie.from(config.getCookieName(), c)
													 .build());
				});

			}));
		};
	}

}
