package com.mosul_hospital.srevecies.nurses.route

import com.mosul_hospital.srevecies.nurses.data.repository.nurseRepo
import com.mosul_hospital.srevecies.nurses.domain.entities.NurseInfo
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.nurseRoute(){
    route("/nurse"){

        get("/all"){
            val nurses = nurseRepo.getAllNurses()
            call.respond(HttpStatusCode.OK, nurses)
        }

        post("/add"){
            try {
                val newNurse = call.receive<NurseInfo>()
                val result = nurseRepo.addNewNurse(newNurse)
                if (result) {
                    call.respond(HttpStatusCode.Created, "Nurse added successfully")
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to add nurse")
                }
            }catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request data")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }

        delete("/{id}"){
            val nurseId = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest, "Missing or malformed id")
            val result = nurseRepo.deleteNurse(nurseId)
            if (result) {
                call.respond(HttpStatusCode.OK, "nurse removed successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to remove nurse")
            }
        }

        put("/{id}"){
            val nurseInfo = call.receive<NurseInfo>()
            val result = nurseRepo.updateNurseInfo(nurseInfo)
            if (result) {
                call.respond(HttpStatusCode.OK, "Nurse updated successfully")
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Failed to update nurse")
            }
        }

    }
}