package com.mosul_hospital.srevecies.nurses.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class NurseInfo(
    val nurseId: String,
    val nurseName: String,
    val nurseSpecification : String
)
