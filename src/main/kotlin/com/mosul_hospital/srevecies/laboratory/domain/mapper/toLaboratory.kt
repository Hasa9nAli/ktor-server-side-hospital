package com.mosul_hospital.srevecies.laboratory.domain.mapper

import com.mosul_hospital.srevecies.laboratory.data.tables.LaboratoryTable
import com.mosul_hospital.srevecies.laboratory.domain.entities.LaboratoryInfo
import org.jetbrains.exposed.sql.ResultRow

fun toLaboratory(row: ResultRow) : LaboratoryInfo {
    return LaboratoryInfo(
        laboratoryEmployeeId = row[LaboratoryTable.laboratoryEmployeeId],
        laboratoryEmployeeName = row[LaboratoryTable.laboratoryEmployeeName],
        laboratoryEmployeeEmail = row[LaboratoryTable.laboratoryEmployeeEmail]
    )

}