package com.mosul_hospital.srevecies.pharmacy.data.repository

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.pharmacy.data.tables.PharmacyTable
import com.mosul_hospital.srevecies.pharmacy.domain.entities.PharmacyInfo
import com.mosul_hospital.srevecies.pharmacy.domain.mapper.toPharmacy
import com.mosul_hospital.srevecies.pharmacy.domain.usecase.PharmacyUseCase
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class PharmacyRepository: PharmacyUseCase {

    override suspend fun addNewPharmacyEmployee(pharmacyEmployeeInfo: PharmacyInfo): Boolean {
        return dbQuery{
            val existingPharmacy = PharmacyTable.selectAll()
                .where { PharmacyTable.pharmacyEmployeeID eq pharmacyEmployeeInfo.pharmacyId }.singleOrNull()
            if (existingPharmacy != null) {
                throw IllegalArgumentException("Pharmacy with id ${pharmacyEmployeeInfo.pharmacyId} already exists.")
            }
            val insertStatement = PharmacyTable.insert {
                it[pharmacyEmployeeID] = pharmacyEmployeeInfo.pharmacyId
                it[pharmacyEmployeeName] = pharmacyEmployeeInfo.pharmacyName
                it[pharmacyEmployeeEmail] = pharmacyEmployeeInfo.pharmacyEmail
            }
            insertStatement.insertedCount > 0
        }
    }

    override suspend fun deletePharmacyEmployee(pharmacyEmployeeInfoId: String): Boolean {
        return dbQuery {
            PharmacyTable.deleteWhere { PharmacyTable.pharmacyEmployeeID eq  pharmacyEmployeeInfoId} > 0
        }
    }

    override suspend fun updatePharmacyEmployee(pharmacyEmployeeInfo: PharmacyInfo): Boolean {
        return dbQuery {
            PharmacyTable.update ({ PharmacyTable.pharmacyEmployeeID eq pharmacyEmployeeInfo.pharmacyId }){
                it[pharmacyEmployeeName] = pharmacyEmployeeInfo.pharmacyName
                it[pharmacyEmployeeEmail] = pharmacyEmployeeInfo.pharmacyEmail
            } > 0
        }
    }

    override suspend fun getAllPharmacyEmployees(): List<PharmacyInfo> {
        return dbQuery {
            try {
                PharmacyTable.selectAll().map { row ->
                    toPharmacy(row)
                }
            }catch (e: Exception){
                throw e
            }
        }
    }
}

val pharmacyRepo = PharmacyRepository()
