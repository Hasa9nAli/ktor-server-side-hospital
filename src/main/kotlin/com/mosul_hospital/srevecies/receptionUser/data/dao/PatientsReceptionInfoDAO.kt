package com.mosul_hospital.srevecies.receptionUser.data.dao

import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo

interface PatientsReceptionInfoDAO {

    suspend fun insertPatientInfo(patientReceptionInfo: PatientInitInfo): Boolean

    suspend fun getPatientInfo(patientId: String): PatientInitInfo?

    suspend fun getPatientByName(patientName: String): List<PatientInitInfo>

    suspend fun getAllPatients(): List<PatientInitInfo>

}