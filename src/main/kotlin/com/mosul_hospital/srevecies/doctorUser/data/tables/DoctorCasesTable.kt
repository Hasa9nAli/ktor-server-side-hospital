package com.mosul_hospital.srevecies.doctorUser.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object DoctorCasesTable : Table() {
    val doctorId = varchar("doctorId", 50).references(DoctorInfoTable.doctorId, onDelete = ReferenceOption.CASCADE)
    val caseId = varchar("caseId", 50).references(PatientCaseTable.caseId, onDelete = ReferenceOption.CASCADE)
    val department = varchar("department", 50)

    override val primaryKey = PrimaryKey(doctorId, caseId)
}