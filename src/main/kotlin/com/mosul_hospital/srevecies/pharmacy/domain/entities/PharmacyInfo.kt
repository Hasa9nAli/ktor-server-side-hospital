package com.mosul_hospital.srevecies.pharmacy.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class PharmacyInfo(
    val pharmacyId : String,
    val pharmacyName : String,
    val pharmacyEmail : String
)
