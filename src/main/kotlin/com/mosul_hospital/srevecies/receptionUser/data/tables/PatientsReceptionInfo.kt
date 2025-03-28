package com.mosul_hospital.srevecies.receptionUser.data.tables

import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import java.util.Base64


object PatientsReceptionInfo: Table("patients_reception_info") {
    val patientId = varchar("patient_id", 64)
    val patientFullName = varchar("patient_full_name", 100)
    val patientMotherName = varchar("patient_mother_name", 100)
    val patientAge = integer("patient_age")
    val patientPhoneNumber = varchar("patient_phone_number", 20)
    val gender = varchar("gender", 12)
    val patientCompanionName = varchar("patient_companion_name", 100)
    val patientCompanionPhoneNumber = varchar("patient_companion_phone_number", 20)
    val maritalStatus = varchar("marital_status", 20)
    val bloodType = varchar("blood_type", 5)
    val job = varchar("job", 50)
    val howArriveToHospital = varchar("how_arrive_to_hospital", 50)
    val previousSurgeryHistory = text("previous_surgery_history")
    val doctorName = varchar("doctor_name", 100)
    val isHaveAllergyToTreatment = bool("is_have_allergy_to_treatment")
    val isHavSurgeryHistory = bool("is_have_surgery_history")
    val attachment = text("attachment")
    val isDoctorSignature = bool("is_doctor_signature").default(false)
    val isLaboratorySignature = bool("is_laboratory_signature").default(false)
    val isAcceptPharmacySignature = bool("is_accept_pharmacy_signature").default(false)
    val isRejectionPharmacySignature = bool("is_rejection_pharmacy_signature").default(false)
    val isTreatmentIsDone = bool("is_treatment_done").default(false)
        override val primaryKey: PrimaryKey = PrimaryKey(patientId)

}

fun toPatientReceptionInfo(row: ResultRow): PatientInitInfo {
    val attachmentList = row[PatientsReceptionInfo.attachment].let {
        Json.decodeFromString<List<String>>(it).map {
            Base64.getDecoder().decode(it)
        }
    }

    return PatientInitInfo(
        patientId = row[PatientsReceptionInfo.patientId],
        patientFullName = row[PatientsReceptionInfo.patientFullName],
        patientMotherName = row[PatientsReceptionInfo.patientMotherName],
        patientAge = row[PatientsReceptionInfo.patientAge],
        patientPhoneNumber = row[PatientsReceptionInfo.patientPhoneNumber],
        patientGender = row[PatientsReceptionInfo.gender],
        patientCompanionName = row[PatientsReceptionInfo.patientCompanionName],
        patientCompanionPhoneNumber = row[PatientsReceptionInfo.patientCompanionPhoneNumber],
        maritalStatus = row[PatientsReceptionInfo.maritalStatus],
        bloodType = row[PatientsReceptionInfo.bloodType],
        job = row[PatientsReceptionInfo.job],
        howArriveToHospital = row[PatientsReceptionInfo.howArriveToHospital],
        previousSurgeryHistory = row[PatientsReceptionInfo.previousSurgeryHistory],
        doctorName = row[PatientsReceptionInfo.doctorName],
        isHaveAllergyToTreatment = row[PatientsReceptionInfo.isHaveAllergyToTreatment],
        isHavSurgeryHistory = row[PatientsReceptionInfo.isHavSurgeryHistory],
        attachment = attachmentList,
        isDoctorSignature = row[PatientsReceptionInfo.isDoctorSignature],
        isLaboratorySignature = row[PatientsReceptionInfo.isLaboratorySignature],
        isAcceptPharmacySignature = row[PatientsReceptionInfo.isAcceptPharmacySignature],
        isRejectionPharmacySignature = row[PatientsReceptionInfo.isRejectionPharmacySignature],
        isTreatmentIsDone = row[PatientsReceptionInfo.isTreatmentIsDone]
    )
}

//// create table
//object PatientsReceptionInfo: Table(){
//    val patientId = varchar("patientId", 64)
//    val patientFullName = varchar("patientFullName", 64)
//    val patientMotherName = varchar("patientMotherName", 64)
//    val patientAge = integer("patientAge")
//    val patientPhoneNumber = varchar("patientPhoneNumber", 64)
//    val gender = varchar("gender",12)
//    val patientCompanionName = varchar("patientCompanionName", 64)
//    val patientCompanionPhoneNumber = varchar("patientCompanionPhoneNumber", 64)
//    val maritalStatus = varchar("maritalStatus", 64)
//    val isHaveAllergyToTreatment = bool("isHaveAllergyToTreatment")
//    val job = varchar("job", 64)
//    val isHavSurgeryHistory = bool("isHavSurgeryHistory")
//    val previousSurgeryHistory = varchar("previousSurgeryHistory", 64)
//    val howArriveToHospital = varchar("howArriveToHospital", 64)
//    val doctorName = varchar("doctorName", 64)
//    val bloodType = varchar("bloodType", 64)
//    val attachment = text("attachment")  // Will store as Base64 encoded strings
//    val isDoctorSignature = bool("isDoctorSignature").default(false)
//    val isLaboratorySignature = bool("isLaboratorySignature").default(false)
//    val isAcceptPharmacySignature = bool("isAcceptPharmacySignature").default(false)
//    val isRejectionPharmacySignature = bool("isRejectionPharmacySignature").default(false)
//    val isTreatmentIsDone = bool("isTreatmentIsDone").default(false)
//
//    override val primaryKey: PrimaryKey = PrimaryKey(patientId)
//}
//
//// mapper function
//fun toPatientReceptionInfo(row: org.jetbrains.exposed.sql.ResultRow): PatientInitInfo =
//    PatientInitInfo(
//        patientId = row[PatientsReceptionInfo.patientId],
//        patientFullName = row[PatientsReceptionInfo.patientFullName],
//        patientMotherName = row[PatientsReceptionInfo.patientMotherName],
//        patientAge = row[PatientsReceptionInfo.patientAge],
//        patientPhoneNumber = row[PatientsReceptionInfo.patientPhoneNumber],
//        patientGender = row[PatientsReceptionInfo.gender],
//        patientCompanionName = row[PatientsReceptionInfo.patientCompanionName],
//        patientCompanionPhoneNumber = row[PatientsReceptionInfo.patientCompanionPhoneNumber],
//        maritalStatus = row[PatientsReceptionInfo.maritalStatus],
//        previousSurgeryHistory = row[PatientsReceptionInfo.previousSurgeryHistory],
//        bloodType = row[PatientsReceptionInfo.bloodType],
//        job = row[PatientsReceptionInfo.job],
//        doctorName = row[PatientsReceptionInfo.doctorName],
//        isHaveAllergyToTreatment = row[PatientsReceptionInfo.isHaveAllergyToTreatment],
//        isHavSurgeryHistory = row[PatientsReceptionInfo.isHavSurgeryHistory],
//        howArriveToHospital = row[PatientsReceptionInfo.howArriveToHospital],
//        attachment = row[PatientsReceptionInfo.attachment]?.let {
//            Json.decodeFromString<List<String>>(it).map { Base64.getDecoder().decode(it) }
//        } ?: emptyList(),
//        isDoctorSignature = row[PatientsReceptionInfo.isDoctorSignature],
//        isLaboratorySignature = row[PatientsReceptionInfo.isLaboratorySignature],
//        isAcceptPharmacySignature = row[PatientsReceptionInfo.isAcceptPharmacySignature],
//        isRejectionPharmacySignature = row[PatientsReceptionInfo.isRejectionPharmacySignature],
//        isTreatmentIsDone = row[PatientsReceptionInfo.isTreatmentIsDone]
//
//    )
