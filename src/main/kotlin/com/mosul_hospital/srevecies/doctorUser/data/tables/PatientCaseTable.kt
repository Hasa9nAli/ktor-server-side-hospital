package com.mosul_hospital.srevecies.doctorUser.data.tables

import org.jetbrains.exposed.sql.Table

object PatientCaseTable : Table() {
        val caseId = varchar("caseId", 50)
        val patientId = varchar("patientId", 50) // This should reference PatientInitInfo
        val status = varchar("status", 50) // Status of the case, e.g., "current" or "treated"

    override val primaryKey: PrimaryKey = PrimaryKey(caseId)
}