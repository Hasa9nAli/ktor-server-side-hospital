package com.mosul_hospital.srevecies.nurses.domain.usecase

import com.mosul_hospital.srevecies.nurses.domain.entities.NurseInfo

interface NurseUseCase{

    suspend fun addNewNurse(nurseInfo:NurseInfo ): Boolean

    suspend fun deleteNurse(nurseId: String): Boolean

    suspend fun updateNurseInfo(nurseInfo: NurseInfo): Boolean

    suspend fun getAllNurses(): List<NurseInfo>


}