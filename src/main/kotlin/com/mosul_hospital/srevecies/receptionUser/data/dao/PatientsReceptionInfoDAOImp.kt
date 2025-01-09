package com.mosul_hospital.srevecies.receptionUser.data.dao

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import com.mosul_hospital.srevecies.receptionUser.data.tables.PatientsReceptionInfo
import com.mosul_hospital.srevecies.receptionUser.data.tables.toPatientReceptionInfo
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.selectAll

class PatientsReceptionInfoDAOImp: PatientsReceptionInfoDAO {
    override suspend fun insertPatientInfo(patientReceptionInfo: PatientInitInfo): Boolean {
        val queryResult = dbQuery{
            val insertStatement = PatientsReceptionInfo.insert {
                it[patientId] = patientReceptionInfo.patientId
                it[patientFullName] = patientReceptionInfo.patientFullName
                it[patientMotherName] = patientReceptionInfo.patientMotherName
                it[patientAge] = patientReceptionInfo.patientAge
                it[patientPhoneNumber] = patientReceptionInfo.patientPhoneNumber
                it[patientCompanionName] = patientReceptionInfo.patientCompanionName
                it[gender] = patientReceptionInfo.patientGender
                it[bloodType] = patientReceptionInfo.bloodType
                it[isHaveAllergyToTreatment] = patientReceptionInfo.isHaveAllergyToTreatment
                it[isHavSurgeryHistory] = patientReceptionInfo.isHavSurgeryHistory
                it[howArriveToHospital] = patientReceptionInfo.howArriveToHospital
                it[job] = patientReceptionInfo.job
                it[maritalStatus] = patientReceptionInfo.maritalStatus
                it[patientCompanionPhoneNumber] = patientReceptionInfo.patientCompanionPhoneNumber
                it[previousSurgeryHistory] = patientReceptionInfo.previousSurgeryHistory
                it[doctorName] = patientReceptionInfo.doctorName
                     }
                insertStatement.resultedValues?.singleOrNull()?.let { row ->
                     toPatientReceptionInfo(row)
                }
        }
        return queryResult != null
    }

    override suspend fun getPatientInfo(patientId: String): PatientInitInfo? {
        return dbQuery {
            PatientsReceptionInfo
                .selectAll().where { PatientsReceptionInfo.patientId eq patientId }
                .map { row -> toPatientReceptionInfo(row) }
                .singleOrNull()
        }

    }

    override suspend fun getPatientByName(patientName: String): List<PatientInitInfo> {
        return dbQuery {
            PatientsReceptionInfo
                .selectAll().where { PatientsReceptionInfo.patientFullName.lowerCase() like "%${patientName.lowercase()}%" }
                .map { toPatientReceptionInfo(it) }
        }
    }

    override suspend fun getAllPatients(): List<PatientInitInfo> {
        return dbQuery {
            PatientsReceptionInfo
                .selectAll()
                .map { toPatientReceptionInfo(it) }
        }
    }

    override suspend fun deletePatientById(patientId: String): Boolean {
        return dbQuery {
            PatientsReceptionInfo.deleteWhere { PatientsReceptionInfo.patientId eq patientId } > 0
        }
    }

}

val patientsReceptionDAO = PatientsReceptionInfoDAOImp()