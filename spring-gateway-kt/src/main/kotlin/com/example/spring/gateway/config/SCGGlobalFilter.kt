package com.example.spring.gateway.config

import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange

@Component
class SCGGlobalFilter : GatewayFilterFactory<SCGGlobalFilter.Config> {

    private val logger = KotlinLogging.logger {}

    override fun apply(config: Config?): GatewayFilter {
        logger.info { "Enter to global-filter" }

        return GatewayFilter { exchange, chain ->
            validateRequest(exchange)

            chain.filter(exchange)
        }
    }

    private fun validateRequest(exchange: ServerWebExchange): ServerHttpRequest {
        return exchange.request.mutate().header("header-global-scg", "header-global-scg").build()
    }

    override fun getConfigClass(): Class<Config> {
        return Config::class.java
    }

    override fun newConfig(): Config {
        return Config(SCGGlobalFilter::class.simpleName)
    }

    data class Config(
            private val name: String?
    )
}