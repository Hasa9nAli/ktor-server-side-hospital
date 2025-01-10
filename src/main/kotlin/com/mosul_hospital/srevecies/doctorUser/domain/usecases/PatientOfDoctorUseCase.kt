package com.mosul_hospital.srevecies.doctorUser.domain.usecases

import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo

interface PatientOfDoctorUseCase {
    suspend fun getCurrentPatientsByDepartment(department: String, doctorId: String): List<PatientInitInfo>
     suspend fun getTreatedPatients(doctorId: String): List<PatientInitInfo>
}