package com.mosul_hospital

import com.mosul_hospital.srevecies.doctorUser.data.repository.doctorRepo
import com.mosul_hospital.srevecies.doctorUser.route.doctorRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import receptionRoute

fun Application.configureRouting() {
    routing {
        receptionRoute()
        doctorRoutes()
    }
}
