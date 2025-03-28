@file:Suppress("DEPRECATION")

import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import com.mosul_hospital.srevecies.receptionUser.domain.repositorty.receptionRepository.receptionPermissionRepo
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.request.receive
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route



import kotlinx.serialization.json.Json

fun Route.receptionRoute() {
    route("/reception") {
        get {
            call.respondText(
                text = "Hello, Reception!",
                status = HttpStatusCode.OK
            )
        }

        // JSON endpoint
        post("/addNewPatient") {
            try {
                val patientInfo = call.receive<PatientInitInfo>()

                if (patientInfo.patientFullName.isBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "patientFullName is required")
                    return@post
                }

                val result = receptionPermissionRepo.insertPatientInfo(
                    patientFullName = patientInfo.patientFullName,
                    patientMotherName = patientInfo.patientMotherName,
                    patientAge = patientInfo.patientAge,
                    patientPhoneNumber = patientInfo.patientPhoneNumber,
                    patientGender = patientInfo.patientGender,
                    patientCompanionName = patientInfo.patientCompanionName,
                    patientCompanionPhoneNumber = patientInfo.patientCompanionPhoneNumber,
                    maritalStatus = patientInfo.maritalStatus,
                    bloodType = patientInfo.bloodType,
                    job = patientInfo.job,
                    howArriveToHospital = patientInfo.howArriveToHospital,
                    previousSurgeryHistory = patientInfo.previousSurgeryHistory,
                    doctorName = patientInfo.doctorName,
                    isHaveAllergyToTreatment = patientInfo.isHaveAllergyToTreatment,
                    isHavSurgeryHistory = patientInfo.isHavSurgeryHistory,
                    attachment = patientInfo.attachment,
                    isDoctorSignature = patientInfo.isDoctorSignature,
                    isLaboratorySignature = patientInfo.isLaboratorySignature,
                    isAcceptPharmacySignature = patientInfo.isAcceptPharmacySignature,
                    isRejectionPharmacySignature = patientInfo.isRejectionPharmacySignature,
                    isTreatmentIsDone = patientInfo.isTreatmentIsDone
                )

                if (result != null) {
                    call.respond(HttpStatusCode.OK, result)
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "Error adding new patient")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Error: ${e.message}")
            }
        }

        // Multipart endpoint for file uploads
        post("/addPatientWithAttachments") {
            try {
                val multipart = call.receiveMultipart()
                var patientInfo: PatientInitInfo? = null
                val attachments = mutableListOf<ByteArray>()

                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {
                            if (part.name == "patient") {
                                patientInfo = Json.decodeFromString(part.value)
                            }
                        }
                        is PartData.FileItem -> {
                            if (part.name == "attachments") {
                                attachments.add(part.streamProvider().readBytes())
                            }
                        }
                        else -> {}
                    }
                    part.dispose()
                }

                if (patientInfo == null) {
                    call.respond(HttpStatusCode.BadRequest, "Patient data is required")
                    return@post
                }

                val result = receptionPermissionRepo.insertPatientInfo(
                    patientFullName = patientInfo?.patientFullName ?: "",
                    patientMotherName = patientInfo?.patientMotherName ?: "" ,
                    patientAge = patientInfo?.patientAge ?: 0,
                    patientPhoneNumber = patientInfo?.patientPhoneNumber ?: "",
                    patientGender = patientInfo?.patientGender ?: "",
                    patientCompanionName = patientInfo?.patientCompanionName?: "",
                    patientCompanionPhoneNumber = patientInfo?.patientCompanionPhoneNumber ?: "",
                    maritalStatus = patientInfo?.maritalStatus ?: "",
                    bloodType = patientInfo?.bloodType ?: "",
                    job = patientInfo?.job ?: "",
                    howArriveToHospital = patientInfo?.howArriveToHospital ?: "",
                    previousSurgeryHistory = patientInfo?.previousSurgeryHistory ?:"",
                    doctorName = patientInfo?.doctorName ?: "",
                    isHaveAllergyToTreatment = patientInfo?.isHaveAllergyToTreatment ?: false,
                    isHavSurgeryHistory = patientInfo?.isHavSurgeryHistory?: false,
                    attachment = attachments,
                    isDoctorSignature = patientInfo?.isDoctorSignature ?: false,
                    isLaboratorySignature = patientInfo?.isLaboratorySignature ?: false,
                    isAcceptPharmacySignature = patientInfo?.isAcceptPharmacySignature ?: false,
                    isRejectionPharmacySignature = patientInfo?.isRejectionPharmacySignature ?: false,
                    isTreatmentIsDone = patientInfo?.isTreatmentIsDone ?: false
                )

                if (result != null) {
                    call.respond(HttpStatusCode.Created, result)
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to save patient")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Error: ${e.message}")
            }
        }

        get("/allPatients") {
            try {
                val patients = receptionPermissionRepo.getAllPatients()
                if (patients.isNotEmpty()) {
                    call.respond(HttpStatusCode.OK, patients)
                } else {
                    call.respond(HttpStatusCode.NotFound, "No patients found")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }

        get("/patientByName") {
            val patientName = call.request.queryParameters["name"]
            if (patientName.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Query parameter 'name' is required")
                return@get
            }

            try {
                val patients = receptionPermissionRepo.getPatientByName(patientName)
                if (patients.isNotEmpty()) {
                    call.respond(HttpStatusCode.OK, patients)
                } else {
                    call.respond(HttpStatusCode.NotFound, "No patients found with name '$patientName'")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }

        delete("/deletePatient/{patientId}") {
            val patientId = call.parameters["patientId"]
            if (patientId.isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Patient ID is required")
                return@delete
            }

            try {
                val deleted = receptionPermissionRepo.deletePatientById(patientId)
                if (deleted) {
                    call.respond(HttpStatusCode.OK, "Patient deleted successfully")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Patient not found")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }
    }
}
