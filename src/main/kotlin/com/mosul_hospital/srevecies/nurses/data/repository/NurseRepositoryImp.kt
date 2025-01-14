package com.mosul_hospital.srevecies.nurses.data.repository

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.nurses.data.tables.NurseTable
import com.mosul_hospital.srevecies.nurses.domain.entities.NurseInfo
import com.mosul_hospital.srevecies.nurses.domain.mapper.toNurse
import com.mosul_hospital.srevecies.nurses.domain.usecase.NurseUseCase
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class NurseRepositoryImp: NurseUseCase {
    override suspend fun addNewNurse(nurseInfo: NurseInfo): Boolean {
        return dbQuery {
            val existingDoctor = NurseTable.selectAll()
                .where { NurseTable.nurseId eq nurseInfo.nurseId }.singleOrNull()
            if (existingDoctor != null) {
                throw IllegalArgumentException("Nurse with id ${nurseInfo.nurseId} already exists.")
            }
            val insertStatement = NurseTable.insert {
                it[nurseId] = nurseInfo.nurseId
                it[nurseName] = nurseInfo.nurseName
                it[nurseSpecification] = nurseInfo.nurseSpecification
            }
            insertStatement.insertedCount > 0
        }

    }

    override suspend fun deleteNurse(nurseId: String): Boolean {
        return dbQuery {
            NurseTable.deleteWhere { NurseTable.nurseId eq nurseId } > 0
        }
    }

    override suspend fun updateNurseInfo(nurseInfo: NurseInfo): Boolean {
        return dbQuery {
            NurseTable.update ({ NurseTable.nurseId eq nurseInfo.nurseId }){
                it[nurseName] = nurseInfo.nurseName
                it[nurseSpecification] = nurseInfo.nurseSpecification
            } > 0
        }
    }

    override suspend fun getAllNurses(): List<NurseInfo> {
        return dbQuery {
            try {
                NurseTable.selectAll().map{row ->
                    toNurse(row)
                }
            }catch (e: Exception){
                throw e
            }
        }
    }
}

val nurseRepo = NurseRepositoryImp()