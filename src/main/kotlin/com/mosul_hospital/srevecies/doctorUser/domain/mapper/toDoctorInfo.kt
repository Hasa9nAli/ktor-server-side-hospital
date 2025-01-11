package com.mosul_hospital.srevecies.doctorUser.domain.mapper

import com.mosul_hospital.srevecies.doctorUser.data.tables.DoctorInfoTable
import com.mosul_hospital.srevecies.doctorUser.domain.entities.DoctorInfo
import org.jetbrains.exposed.sql.ResultRow

fun toDoctorInfo(row: ResultRow): DoctorInfo {
    return DoctorInfo(
        doctorId = row[DoctorInfoTable.doctorId],
        doctorName = row[DoctorInfoTable.doctorName],
        doctorSpecification = row[DoctorInfoTable.doctorSpecification],
    )
}