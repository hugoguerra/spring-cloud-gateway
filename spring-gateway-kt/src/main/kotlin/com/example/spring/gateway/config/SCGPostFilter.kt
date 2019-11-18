package com.example.spring.gateway.config

import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory
import org.springframework.stereotype.Component

@Component
class SCGPostFilter : GatewayFilterFactory<SCGPostFilter.Config> {

    private val logger = KotlinLogging.logger {}

    override fun apply(config: Config?): GatewayFilter {
        logger.info { "Enter to post-filter" }

        return GatewayFilter { exchange, chain ->
            chain.filter(exchange)

            /**
             * .then(Mono.fromRunnable {
            exchange.response.headers.forEach {
            println(it.key + " - " + it.value)
            }
            })
             */
        }
    }

    override fun getConfigClass(): Class<Config> {
        return Config::class.java
    }

    override fun newConfig(): Config {
        return Config(SCGPostFilter::class.simpleName)
    }

    data class Config(
            private val name: String?
    )
}