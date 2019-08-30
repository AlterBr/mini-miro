package com.alter.minimiro.frontend

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.server.spring.scope.RequestContextFilter
import org.springframework.stereotype.Component
import javax.ws.rs.ApplicationPath

@Suppress("LeakingThis")
@ApplicationPath("/api")
@Component
class RestClientApplication : ResourceConfig() {
    init {
        packages("com.alter.minimiro.frontend.handler")
        register(RequestContextFilter::class.java)
        register(CustomJsonProvider())
    }
}