package com.mosul_hospital.srevecies.pharmacy.domain.usecase

import com.mosul_hospital.srevecies.pharmacy.domain.entities.PharmacyInfo

interface PharmacyUseCase {

    suspend fun addNewPharmacyEmployee(pharmacyEmployeeInfo: PharmacyInfo): Boolean

    suspend fun deletePharmacyEmployee(pharmacyEmployeeInfoId: String): Boolean

    suspend fun updatePharmacyEmployee(pharmacyEmployeeInfo: PharmacyInfo): Boolean

    suspend fun getAllPharmacyEmployees(): List<PharmacyInfo>
}