package com.mosul_hospital.srevecies.receptionUser.domain.repositorty.receptionRepository

import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo

interface ReceptionPermissionRepository {

    suspend fun insertPatientInfo(
        patientFullName: String,
        patientMotherName: String,
        patientAge: Int,
        patientPhoneNumber: String,
        patientGender: String,
        patientCompanionName: String,
        patientCompanionPhoneNumber: String,
        maritalStatus: String,
        bloodType: String,
        job: String,
        howArriveToHospital: String,
        previousSurgeryHistory: String,
        doctorName: String,
        isHaveAllergyToTreatment: Boolean,
        isHavSurgeryHistory: Boolean
    ): PatientInitInfo?

    suspend fun getPatientInfo(patientId: String): PatientInitInfo?

    suspend fun getPatientByName(patientName: String): List<PatientInitInfo>

    suspend fun getAllPatients(): List<PatientInitInfo>

    suspend fun deletePatientById(patientId: String): Boolean

}