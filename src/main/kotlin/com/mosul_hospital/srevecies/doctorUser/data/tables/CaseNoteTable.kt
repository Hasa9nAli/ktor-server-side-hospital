package com.mosul_hospital.srevecies.doctorUser.data.tables

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CaseNotesTable : Table() {
    val noteId = integer("noteId").autoIncrement()
    val caseId = varchar("caseId", 50).references(PatientCaseTable.caseId, onDelete = ReferenceOption.CASCADE)
    val note = text("note")

    override val primaryKey = PrimaryKey(noteId)
}