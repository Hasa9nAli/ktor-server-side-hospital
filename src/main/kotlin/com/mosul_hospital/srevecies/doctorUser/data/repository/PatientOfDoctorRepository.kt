package com.mosul_hospital.srevecies.doctorUser.data.repository

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.doctorUser.data.tables.DoctorCasesTable
import com.mosul_hospital.srevecies.doctorUser.data.tables.PatientCaseTable
import com.mosul_hospital.srevecies.doctorUser.domain.usecases.PatientOfDoctorUseCase
import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import com.mosul_hospital.srevecies.receptionUser.data.tables.PatientsReceptionInfo
import com.mosul_hospital.srevecies.receptionUser.data.tables.toPatientReceptionInfo
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll

class PatientOfDoctorRepository: PatientOfDoctorUseCase {
    override suspend fun getCurrentPatientsByDepartment(department: String, doctorId: String): List<PatientInitInfo> {
        return dbQuery {
            (DoctorCasesTable innerJoin PatientCaseTable innerJoin PatientsReceptionInfo).selectAll().where {
                (DoctorCasesTable.department eq department) and
                        (DoctorCasesTable.doctorId eq doctorId) and
                        (PatientCaseTable.status eq "current")
            }.map { toPatientReceptionInfo(it) }
        }
    }

    override suspend fun getTreatedPatients(doctorId: String): List<PatientInitInfo> {
        return dbQuery {
            (DoctorCasesTable innerJoin PatientCaseTable innerJoin PatientsReceptionInfo).selectAll().where {
                (DoctorCasesTable.doctorId eq doctorId) and
                        (PatientCaseTable.status eq "treated")
            }.map { toPatientReceptionInfo(it) }
        }
    }
}

val patientOfDoctorRepo = PatientOfDoctorRepository()