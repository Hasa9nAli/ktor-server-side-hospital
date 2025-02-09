package com.mosul_hospital

import com.mosul_hospital.srevecies.doctorUser.route.doctorRoutes
import com.mosul_hospital.srevecies.laboratory.route.laboratoryRoute
import com.mosul_hospital.srevecies.nurses.route.nurseRoute
import com.mosul_hospital.srevecies.pharmacy.route.pharmacyRoute
import com.mosul_hospital.srevecies.radology.route.radiologyRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import receptionRoute

fun Application.configureRouting() {
    routing {
        receptionRoute()
        doctorRoutes()
        nurseRoute()
        radiologyRoute()
        laboratoryRoute()
        pharmacyRoute()
    }
}
