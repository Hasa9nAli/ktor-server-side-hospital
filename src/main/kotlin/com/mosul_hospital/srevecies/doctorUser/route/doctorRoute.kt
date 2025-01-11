package com.mosul_hospital.srevecies.doctorUser.route

import com.mosul_hospital.srevecies.doctorUser.data.repository.doctorRepo
import com.mosul_hospital.srevecies.doctorUser.domain.entities.DoctorInfo
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route


fun Route.doctorRoutes() {
    route("/doctors") {

        // Get all doctors
        get("/all") {
            val doctors = doctorRepo.getAllDoctors()
            call.respond(HttpStatusCode.OK, doctors)
        }



        // Add a new doctor
        post("/add") {
            try {
                val doctorInfo = call.receive<DoctorInfo>()
                val result = doctorRepo.addDoctor(doctorInfo)
                if (result) {
                    call.respond(HttpStatusCode.Created, "Doctor added successfully")
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to add doctor")
                }
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request data")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }

        // Remove a doctor
        delete("/{id}") {
            val doctorId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val result = doctorRepo.removeDoctor(doctorId)
            if (result) {
                call.respond(HttpStatusCode.OK, "Doctor removed successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to remove doctor")
            }
        }

        // Update doctor information
        put("/update") {
            val doctorInfo = call.receive<DoctorInfo>()
            val result = doctorRepo.updateDoctorInfo(doctorInfo)
            if (result) {
                call.respond(HttpStatusCode.OK, "Doctor updated successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to update doctor")
            }
        }
    }
}