package com.mosul_hospital.srevecies.receptionUser.data.model

import kotlinx.serialization.Serializable
import java.util.Base64

@Serializable
data class PatientInitInfo(
    val patientId: String,
    val patientFullName: String,
    val patientMotherName: String,
    val patientAge: Int,
    val patientPhoneNumber: String,
    val patientGender: String,
    val patientCompanionName: String,
    val patientCompanionPhoneNumber: String,
    val maritalStatus: String,
    val bloodType: String,
    val job: String,
    val howArriveToHospital: String,
    val previousSurgeryHistory: String,
    val doctorName: String,
    val isHaveAllergyToTreatment: Boolean,
    val isHavSurgeryHistory: Boolean,
    val attachment: List<ByteArray> = emptyList(),
    val isDoctorSignature: Boolean,
    val isLaboratorySignature: Boolean,
    val isAcceptPharmacySignature: Boolean,
    val isRejectionPharmacySignature: Boolean,
    val isTreatmentIsDone: Boolean
) {
    fun getAttachmentBase64(): List<String> {
        return attachment.map { Base64.getEncoder().encodeToString(it) }
    }
}