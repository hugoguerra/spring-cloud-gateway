package com.example.spring.gateway.config

import mu.KotlinLogging
import org.apache.logging.log4j.util.Strings
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SCGPreFilter : GatewayFilterFactory<SCGPreFilter.Config> {

    private val logger = KotlinLogging.logger {}

    override fun apply(config: Config?): GatewayFilter {
        logger.info { "Enter to pre-filter" }

        return GatewayFilter { exchange, chain ->
            if (this.isInvalidRequest(exchange)) {
                logger.error { "Token not valid" }
                this.onError(exchange, HttpStatus.UNAUTHORIZED)
            } else {
                // ADD parameter header for student service
                val request = exchange.request.mutate().header("customer", "header-pre-scg").build()
                chain.filter(exchange.mutate().request(request).build())
            }
        }
    }

    private fun onError(exchange: ServerWebExchange, httpStatus: HttpStatus): Mono<Void> {
        val response = exchange.response

        response.statusCode = httpStatus
        return response.setComplete()
    }

    private fun isInvalidRequest(exchange: ServerWebExchange): Boolean {
        val token = exchange.request.headers.get("Authorization")
        return token == null || Strings.isBlank(token.toString())
    }

    override fun getConfigClass(): Class<Config> {
        return Config::class.java
    }

    override fun newConfig(): Config {
        return Config(SCGPreFilter::class.simpleName)
    }

    data class Config(
            private val name: String?
    )
}