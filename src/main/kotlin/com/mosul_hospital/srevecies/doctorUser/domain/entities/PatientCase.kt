package com.mosul_hospital.srevecies.doctorUser.domain.entities

import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import kotlinx.serialization.Serializable

@Serializable
data class PatientCase(
    val caseId: String,
    val patientInitInfo: PatientInitInfo,
    val previousAttachmentImages: List<ByteArray>,
    val currentAttachmentImages: List<ByteArray>,
    val notes: List<String>,
    val status: String
)