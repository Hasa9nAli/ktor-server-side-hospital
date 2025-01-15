package com.mosul_hospital.srevecies.radology.route


import com.mosul_hospital.srevecies.radology.data.repositorty.radiologyRepo
import com.mosul_hospital.srevecies.radology.domain.entities.RadiologyInfo
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.radiologyRoute(){
    route("/radiology"){
        get("/all"){
            val radiologyEmployees = radiologyRepo.getAllRadiology()
            call.respond(HttpStatusCode.OK, radiologyEmployees)
        }

        post("/add"){
            try {
                val newRadiologyEmployee = call.receive<RadiologyInfo>()
                val result = radiologyRepo.addNewRadiology(newRadiologyEmployee)
                if (result) {
                    call.respond(HttpStatusCode.Created, "Radiology employee added successfully")
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to add Radiology employee")
                }
            }catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request data")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }

        delete("/{id}"){
            val radiologyId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val result = radiologyRepo.deleteRadiology(radiologyId)
            if (result) {
                call.respond(HttpStatusCode.OK, "Radiology employee removed successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to remove radiology employee")
            }
        }

        put("/{id}"){
            val radiologyEmployeeInfo = call.receive<RadiologyInfo>()
            val result = radiologyRepo.updateRadiologyInfo(radiologyEmployeeInfo)
            if (result) {
                call.respond(HttpStatusCode.OK, "Radiology employee updated successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to update radiology employee")
            }
        }
    }
}