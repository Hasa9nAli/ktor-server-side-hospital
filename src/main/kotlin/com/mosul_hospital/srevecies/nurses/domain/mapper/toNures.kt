package com.mosul_hospital.srevecies.nurses.domain.mapper

import com.mosul_hospital.srevecies.nurses.data.tables.NurseTable
import com.mosul_hospital.srevecies.nurses.domain.entities.NurseInfo
import org.jetbrains.exposed.sql.ResultRow

fun toNurse(row: ResultRow): NurseInfo{
    return NurseInfo(
        nurseId = row[NurseTable.nurseId],
        nurseName = row[NurseTable.nurseName],
        nurseSpecification = row[NurseTable.nurseSpecification]
    )

}