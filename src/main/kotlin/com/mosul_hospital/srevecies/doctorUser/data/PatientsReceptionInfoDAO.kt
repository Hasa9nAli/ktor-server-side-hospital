package com.mosul_hospital.srevecies.doctorUser.data

import com.mosul_hospital.srevecies.doctorUser.domain.PatientInitInfo

interface PatientsReceptionInfoDAO {

    suspend fun insertPatientInfo(patientReceptionInfo: PatientInitInfo): Boolean

    suspend fun getPatientInfo(patientId: String): PatientInitInfo?

    suspend fun getPatientByName(patientName: String): List<PatientInitInfo>

}