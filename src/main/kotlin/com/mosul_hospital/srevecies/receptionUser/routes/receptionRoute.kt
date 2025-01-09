import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import com.mosul_hospital.srevecies.receptionUser.domain.repositorty.receptionRepository.receptionPermissionRepo
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.receptionRoute(){
    route("/reception"){
        get {
            call.respondText (
                text =  "Hello, Reception!",
                status = HttpStatusCode.OK)
        }
        post("/addNewPatient"){
            try {
                // Receive JSON body as PatientInitInfo
                val patientInfo = call.receive<PatientInitInfo>()
                print(patientInfo)
                // Validate the received data
                if (patientInfo.patientFullName.isBlank()) return@post call.respondText(
                    text = "patientFullName is required",
                    status = HttpStatusCode.BadRequest
                )
                // Add other validations as needed...

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
                    isHavSurgeryHistory = patientInfo.isHavSurgeryHistory
                )
                if(result != null)
                    call.respond(message = result, status = HttpStatusCode.OK)
                else
                    return@post call.respondText(
                        text = "error add new patient ",
                        status = HttpStatusCode.InternalServerError
                    )

            } catch (e: Exception) {
                call.respondText(
                    text = e.message.toString(),
                    status = HttpStatusCode.BadRequest

                )
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
            if (patientName == null || patientName.isBlank()) {
                call.respond(HttpStatusCode.BadRequest, "Query parameter 'name' is required")
                return@get
            }

            try {
                val patients = receptionPermissionRepo.getPatientByName(patientName)
                if (patients.isNotEmpty()) {
                    call.respond(HttpStatusCode.OK, patients)
                } else {
                    call.respond(HttpStatusCode.NotFound, "No patients found with the name $patientName")
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "An error occurred: ${e.message}")
            }
        }

        delete("/deletePatient/{patientId}") {
            val patientId = call.parameters["patientId"]
            if (patientId == null || patientId.isBlank()) {
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