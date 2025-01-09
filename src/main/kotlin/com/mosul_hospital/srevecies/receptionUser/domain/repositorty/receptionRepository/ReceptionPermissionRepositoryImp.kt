package com.mosul_hospital.srevecies.receptionUser.domain.repositorty.receptionRepository

import com.mosul_hospital.srevecies.receptionUser.data.dao.patientsReceptionDAO
import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import java.util.UUID

class ReceptionPermissionRepositoryImp: ReceptionPermissionRepository {

    override suspend fun insertPatientInfo(
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
    ): PatientInitInfo? {
        val newPatient = PatientInitInfo(
            patientId = UUID.randomUUID().toString(),
            patientFullName = patientFullName,
            patientMotherName = patientMotherName,
            patientAge = patientAge,
            patientPhoneNumber = patientPhoneNumber,
            patientGender = patientGender,
            patientCompanionName = patientCompanionName,
            patientCompanionPhoneNumber = patientCompanionPhoneNumber,
            maritalStatus = maritalStatus,
            bloodType = bloodType,
            job = job,
            howArriveToHospital = howArriveToHospital,
            previousSurgeryHistory = previousSurgeryHistory,
            doctorName = doctorName,
            isHaveAllergyToTreatment = isHaveAllergyToTreatment,
            isHavSurgeryHistory = isHavSurgeryHistory
        )

        return if(patientsReceptionDAO.insertPatientInfo(newPatient)) newPatient else null
    }

    override suspend fun getPatientInfo(patientId: String): PatientInitInfo? {
        return patientsReceptionDAO.getPatientInfo(patientId)
    }

    override suspend fun getPatientByName(patientName: String): List<PatientInitInfo> {
        return patientsReceptionDAO.getPatientByName(patientName)
    }

    override suspend fun getAllPatients(): List<PatientInitInfo> {
       return patientsReceptionDAO.getAllPatients()
    }
}
val receptionPermissionRepo = ReceptionPermissionRepositoryImp()