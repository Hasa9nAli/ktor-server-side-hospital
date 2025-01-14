package com.mosul_hospital.srevecies.radology.domain.usecase

import com.mosul_hospital.srevecies.radology.domain.entities.RadiologyInfo

interface RadiologyUseCase {

    suspend fun addNewRadiology(radiologyInfo: RadiologyInfo): Boolean

    suspend fun deleteRadiology(radiologyId: String): Boolean

    suspend fun updateRadiologyInfo(radiologyInfo: RadiologyInfo): Boolean

    suspend fun getAllRadiology(): List<RadiologyInfo>
}