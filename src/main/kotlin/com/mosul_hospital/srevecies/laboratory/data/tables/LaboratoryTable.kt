package com.mosul_hospital.srevecies.laboratory.data.tables

import org.jetbrains.exposed.sql.Table

object LaboratoryTable: Table() {
    val laboratoryEmployeeId = varchar("laboratoryEmployeeId", 64)
    val laboratoryEmployeeName = varchar("laboratoryEmployeeName", 64)
    val laboratoryEmployeeEmail = varchar("laboratoryEmployeeIdEmail", 128)

}