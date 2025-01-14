package com.mosul_hospital

import com.mosul_hospital.srevecies.doctorUser.route.doctorRoutes
import com.mosul_hospital.srevecies.nurses.route.nurseRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import receptionRoute

fun Application.configureRouting() {
    routing {
        receptionRoute()
        doctorRoutes()
        nurseRoute()
    }
}
