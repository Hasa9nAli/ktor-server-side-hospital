package com.mosul_hospital.srevecies.laboratory.route

import com.mosul_hospital.srevecies.laboratory.data.repository.laboratoryRepo
import com.mosul_hospital.srevecies.laboratory.domain.entities.LaboratoryInfo
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.laboratoryRoute() {
    route("/laboratory"){
        get("/all"){
            val nurses = laboratoryRepo.getAllLaboratoryEmployees()
            call.respond(HttpStatusCode.OK, nurses)
        }

        post("/add"){
            try {
                val newLaboratoryEmployee = call.receive<LaboratoryInfo>()
                val result = laboratoryRepo.addNewLaboratoryEmployee(newLaboratoryEmployee)
                if (result) {
                    call.respond(HttpStatusCode.Created, "Laboratory Employee added successfully")
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to add Laboratory Employee")
                }
            }catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request data")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }

        delete("/{id}"){
            val laboratoryId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val result = laboratoryRepo.deleteLaboratoryEmployee(laboratoryId)
            if (result) {
                call.respond(HttpStatusCode.OK, "Laboratory Employee removed successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to remove Laboratory Employee")
            }
        }

        put("/{id}"){
            val laboratoryInfo = call.receive<LaboratoryInfo>()
            val result = laboratoryRepo.updateLaboratoryEmployeeInfo(laboratoryInfo)
            if (result) {
                call.respond(HttpStatusCode.OK, "laboratory updated successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to update laboratory")
            }
        }
    }
}