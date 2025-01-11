package com.mosul_hospital.srevecies.doctorUser.data.repository

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.doctorUser.data.tables.CaseNotesTable
import com.mosul_hospital.srevecies.doctorUser.data.tables.DoctorCasesTable
import com.mosul_hospital.srevecies.doctorUser.data.tables.DoctorInfoTable

import com.mosul_hospital.srevecies.doctorUser.data.tables.PatientCaseTable
import com.mosul_hospital.srevecies.doctorUser.domain.entities.DoctorInfo
import com.mosul_hospital.srevecies.doctorUser.domain.entities.PatientCase
import com.mosul_hospital.srevecies.doctorUser.domain.usecases.DoctorUseCases
import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import com.mosul_hospital.srevecies.receptionUser.data.tables.PatientsReceptionInfo
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class DoctorRepository : DoctorUseCases {

    override suspend fun getDoctorCases(doctorId: String): List<PatientCase> {
        return dbQuery {
            (DoctorCasesTable innerJoin PatientCaseTable innerJoin PatientsReceptionInfo).selectAll().where {
                DoctorCasesTable.doctorId eq doctorId
            }.map { row ->
                val notes = CaseNotesTable.selectAll().where { CaseNotesTable.caseId eq row[PatientCaseTable.caseId] }
                    .map { it[CaseNotesTable.note] }
                toPatientCase(row, notes)
            }
        }
    }

    private suspend fun getCasesForDoctor(doctorId: String): List<PatientCase> {
        return dbQuery {
            (DoctorCasesTable innerJoin PatientCaseTable innerJoin PatientsReceptionInfo).selectAll().where {
                DoctorCasesTable.doctorId eq doctorId
            }.map { row ->
                val notes = CaseNotesTable.selectAll().where { CaseNotesTable.caseId eq row[PatientCaseTable.caseId] }
                    .map { it[CaseNotesTable.note] }
                toPatientCase(row, notes)
            }
        }
    }

    override suspend fun addDoctor(doctorInfo: DoctorInfo): Boolean {
        return dbQuery {
            val insertStatement = DoctorInfoTable.insert {
                it[doctorId] = doctorInfo.doctorId
                it[doctorName] = doctorInfo.doctorName
                it[doctorSpecification] = doctorInfo.doctorSpecification

            }
            insertStatement.insertedCount > 0
        }
    }

    override suspend fun removeDoctor(doctorId: String): Boolean {
        return dbQuery {
            DoctorInfoTable.deleteWhere { DoctorInfoTable.doctorId eq doctorId } > 0
        }
    }

    override suspend fun updateDoctorInfo(doctorInfo: DoctorInfo): Boolean {
        return dbQuery {
            DoctorInfoTable.update({ DoctorInfoTable.doctorId eq doctorInfo.doctorId }) {
                it[doctorName] = doctorInfo.doctorName
                it[doctorSpecification] = doctorInfo.doctorSpecification
            } > 0
        }
    }

    override suspend fun getAllDoctors(): List<DoctorInfo> {
        return dbQuery {
            DoctorInfoTable.selectAll().map { row ->
                val doctorId = row[DoctorInfoTable.doctorId]
                val cases = runBlocking { getCasesForDoctor(doctorId) }
                toDoctorInfo(row, cases)
            }
        }
    }

    private fun toDoctorInfo(row: ResultRow, cases: List<PatientCase>): DoctorInfo {
        return DoctorInfo(
            doctorId = row[DoctorInfoTable.doctorId],
            doctorName = row[DoctorInfoTable.doctorName],
            doctorSpecification = row[DoctorInfoTable.doctorSpecification],
            casesTreatedByThisDoctor = cases
        )
    }

    private fun toPatientCase(row: ResultRow, notes: List<String> = emptyList()): PatientCase {
        return PatientCase(
            caseId = row[PatientCaseTable.caseId],
            patientInitInfo = toPatientInitInfo(row),
            previousAttachmentImages = emptyList(), // Replace with actual implementation if available
            currentAttachmentImages = emptyList(),  // Replace with actual implementation if available
            notes = notes,
            status = row[PatientCaseTable.status]
        )
    }

    private fun toPatientInitInfo(row: ResultRow): PatientInitInfo {
        return PatientInitInfo(
            patientId = row[PatientsReceptionInfo.patientId],
            patientFullName = row[PatientsReceptionInfo.patientFullName],
            patientMotherName = row[PatientsReceptionInfo.patientMotherName],
            patientAge = row[PatientsReceptionInfo.patientAge],
            patientPhoneNumber = row[PatientsReceptionInfo.patientPhoneNumber],
            patientGender = row[PatientsReceptionInfo.gender],
            patientCompanionName = row[PatientsReceptionInfo.patientCompanionName],
            patientCompanionPhoneNumber = row[PatientsReceptionInfo.patientCompanionPhoneNumber],
            maritalStatus = row[PatientsReceptionInfo.maritalStatus],
            bloodType = row[PatientsReceptionInfo.bloodType],
            job = row[PatientsReceptionInfo.job],
            howArriveToHospital = row[PatientsReceptionInfo.howArriveToHospital],
            previousSurgeryHistory = row[PatientsReceptionInfo.previousSurgeryHistory],
            doctorName = row[PatientsReceptionInfo.doctorName],
            isHaveAllergyToTreatment = row[PatientsReceptionInfo.isHaveAllergyToTreatment],
            isHavSurgeryHistory = row[PatientsReceptionInfo.isHavSurgeryHistory]
        )
    }
}

val doctorRepo = DoctorRepository()