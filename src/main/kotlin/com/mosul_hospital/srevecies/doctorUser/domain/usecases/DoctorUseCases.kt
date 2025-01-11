package com.mosul_hospital.srevecies.doctorUser.domain.usecases

import com.mosul_hospital.srevecies.doctorUser.domain.entities.DoctorInfo
import com.mosul_hospital.srevecies.doctorUser.domain.entities.PatientCase

interface DoctorUseCases {

    suspend fun getDoctorCases(doctorId: String): List<PatientCase>

    suspend fun addDoctor(doctorInfo: DoctorInfo): Boolean

    suspend fun removeDoctor(doctorId: String): Boolean

    suspend fun updateDoctorInfo(doctorInfo: DoctorInfo): Boolean

    suspend fun getAllDoctors(): List<DoctorInfo>
}