package com.mosul_hospital.srevecies.laboratory.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class LaboratoryInfo(
    val laboratoryEmployeeId: String,
    val laboratoryEmployeeName: String,
    val laboratoryEmployeeEmail: String
)
