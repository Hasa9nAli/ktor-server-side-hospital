package com.mosul_hospital.srevecies.laboratory.data.repository

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.laboratory.data.tables.LaboratoryTable
import com.mosul_hospital.srevecies.laboratory.domain.entities.LaboratoryInfo
import com.mosul_hospital.srevecies.laboratory.domain.mapper.toLaboratory
import com.mosul_hospital.srevecies.laboratory.domain.usecase.LaboratoryUseCase
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class LaboratoryRepository: LaboratoryUseCase {
    override suspend fun addNewLaboratoryEmployee(laboratoryEmployeeInfo: LaboratoryInfo): Boolean {
        return dbQuery{
            val existingDoctor = LaboratoryTable.selectAll()
                .where { LaboratoryTable.laboratoryEmployeeId eq laboratoryEmployeeInfo.laboratoryEmployeeId }.singleOrNull()
            if (existingDoctor != null) {
                throw IllegalArgumentException("Radiology with id ${laboratoryEmployeeInfo.laboratoryEmployeeId} already exists.")
            }
            val insertStatement = LaboratoryTable.insert {
                it[laboratoryEmployeeId] = laboratoryEmployeeInfo.laboratoryEmployeeId
                it[laboratoryEmployeeName] = laboratoryEmployeeInfo.laboratoryEmployeeName
                it[laboratoryEmployeeEmail] = laboratoryEmployeeInfo.laboratoryEmployeeEmail
            }
            insertStatement.insertedCount > 0
        }
    }

    override suspend fun deleteLaboratoryEmployee(laboratoryEmployeeId: String): Boolean {
        return dbQuery {
            LaboratoryTable.deleteWhere { LaboratoryTable.laboratoryEmployeeId eq  laboratoryEmployeeId} > 0
        }
    }

    override suspend fun updateLaboratoryEmployeeInfo(laboratoryEmployeeInfo: LaboratoryInfo): Boolean {
        return dbQuery {
            LaboratoryTable.update ({ LaboratoryTable.laboratoryEmployeeId eq laboratoryEmployeeInfo.laboratoryEmployeeId }){
                it[laboratoryEmployeeName] = laboratoryEmployeeInfo.laboratoryEmployeeName
                it[laboratoryEmployeeEmail] = laboratoryEmployeeInfo.laboratoryEmployeeEmail
            } > 0
        }
    }

    override suspend fun getAllLaboratoryEmployees(): List<LaboratoryInfo> {
        return dbQuery {
            try {
                LaboratoryTable.selectAll().map { row ->
                    toLaboratory(row)
                }
            }catch (e: Exception){
                throw e
            }
        }
    }
}

val laboratoryRepo = LaboratoryRepository()