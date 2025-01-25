package com.mosul_hospital.srevecies.laboratory.domain.usecase

import com.mosul_hospital.srevecies.laboratory.domain.entities.LaboratoryInfo

interface LaboratoryUseCase{
    suspend fun addNewLaboratoryEmployee(laboratoryEmployeeInfo: LaboratoryInfo): Boolean

    suspend fun deleteLaboratoryEmployee(laboratoryEmployeeId: String): Boolean

    suspend fun updateLaboratoryEmployeeInfo(laboratoryEmployeeInfo: LaboratoryInfo): Boolean

    suspend fun getAllLaboratoryEmployees(): List<LaboratoryInfo>

}