package com.mosul_hospital.srevecies.radology.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class RadiologyInfo(
    val radiologyId: String,
    val radiologyEmail: String,
    val radiologyName: String,
)