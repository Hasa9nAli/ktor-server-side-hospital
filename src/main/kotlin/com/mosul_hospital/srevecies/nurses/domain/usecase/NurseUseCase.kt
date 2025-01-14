package com.mosul_hospital.srevecies.nurses.domain.usecase

import com.mosul_hospital.srevecies.nurses.domain.entities.NurseInfo

interface NurseUseCase{

    fun addNewNurse(nurseInfo:NurseInfo ): Boolean

    fun deleteNurse(nurseId: String): Boolean

    fun updateNurseInfo(nurseInfo: NurseInfo): Boolean

    fun getAllNurses(): List<NurseInfo>


}