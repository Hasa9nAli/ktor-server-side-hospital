package com.mosul_hospital.srevecies.doctorUser.data.repository

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.doctorUser.data.tables.DoctorInfoTable
import com.mosul_hospital.srevecies.doctorUser.domain.entities.DoctorInfo
import com.mosul_hospital.srevecies.doctorUser.domain.mapper.toDoctorInfo
import com.mosul_hospital.srevecies.doctorUser.domain.usecases.DoctorUseCases
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class DoctorRepository : DoctorUseCases {

    override suspend fun addDoctor(doctorInfo: DoctorInfo): Boolean {
        return dbQuery {
            val existingDoctor = DoctorInfoTable.selectAll().where { DoctorInfoTable.doctorId eq doctorInfo.doctorId }.singleOrNull()
            if (existingDoctor != null) {
                throw IllegalArgumentException("Doctor with id ${doctorInfo.doctorId} already exists.")
            }

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
            try {
                DoctorInfoTable.selectAll().map { row ->
                    toDoctorInfo(row)
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}

val doctorRepo = DoctorRepository()