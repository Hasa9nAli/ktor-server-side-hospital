package com.mosul_hospital.srevecies.doctorUser.domain.usecases

import com.mosul_hospital.srevecies.doctorUser.domain.entities.DoctorInfo

interface DoctorUseCases {


    suspend fun addDoctor(doctorInfo: DoctorInfo): Boolean

    suspend fun removeDoctor(doctorId: String): Boolean

    suspend fun updateDoctorInfo(doctorInfo: DoctorInfo): Boolean

    suspend fun getAllDoctors(): List<DoctorInfo>

    suspend fun updateDoctorSignatureForSpecificPatient(patientId: String, isSigned: Boolean): Boolean
}