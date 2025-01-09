package com.mosul_hospital

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import receptionRoute

fun Application.configureRouting() {
    routing {
        receptionRoute()
    }
}
