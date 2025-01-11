package com.mosul_hospital.srevecies.doctorUser.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class DoctorInfo(
    val doctorId: String,
    val doctorName: String,
    val doctorSpecification: String,
)