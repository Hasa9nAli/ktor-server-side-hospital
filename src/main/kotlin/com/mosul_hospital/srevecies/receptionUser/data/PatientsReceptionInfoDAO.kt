package com.mosul_hospital.srevecies.receptionUser.data

import com.mosul_hospital.srevecies.receptionUser.domain.PatientInitInfo

interface PatientsReceptionInfoDAO {

    suspend fun insertPatientInfo(patientReceptionInfo: PatientInitInfo): Boolean

    suspend fun getPatientInfo(patientId: String): PatientInitInfo?

    suspend fun getPatientByName(patientName: String): List<PatientInitInfo>

}