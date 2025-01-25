package com.mosul_hospital.srevecies.pharmacy.route

import com.mosul_hospital.srevecies.pharmacy.data.repository.pharmacyRepo
import com.mosul_hospital.srevecies.pharmacy.domain.entities.PharmacyInfo
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.pharmacyRoute() {
    route("/pharmacy") {


        get("/all") {
            val pharmacyEmployees = pharmacyRepo.getAllPharmacyEmployees()
            call.respond(HttpStatusCode.OK, pharmacyEmployees)
        }

        post("/add") {
            try {
                val newPharmacyEmployee = call.receive<PharmacyInfo>()
                val result = pharmacyRepo.addNewPharmacyEmployee(newPharmacyEmployee)
                if (result) {
                    call.respond(
                        HttpStatusCode.Created,
                        "Pharmacy Employee employee added successfully"
                    )
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Failed to add Pharmacy employee"
                    )
                }
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request data")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }

            delete("/{id}") {
                val pharmacyEmployeeId = call.parameters["id"] ?: return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    "Missing or malformed id"
                )
                val result = pharmacyRepo.deletePharmacyEmployee(pharmacyEmployeeId)
                if (result) {
                    call.respond(
                        HttpStatusCode.OK,
                        "Pharmacy Employee employee removed successfully"
                    )
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Failed to remove Pharmacy employee"
                    )
                }
            }

            put("/{id}") {
                val pharmacyEmployeeInfo = call.receive<PharmacyInfo>()
                val result = pharmacyRepo.updatePharmacyEmployee(pharmacyEmployeeInfo)
                if (result) {
                    call.respond(HttpStatusCode.OK, "pharmacy employee updated successfully")
                } else {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Failed to update pharmacy employee"
                    )
                }
            }
        }
    }
}