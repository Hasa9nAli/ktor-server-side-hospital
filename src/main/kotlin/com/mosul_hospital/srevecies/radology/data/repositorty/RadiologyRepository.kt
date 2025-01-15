package com.mosul_hospital.srevecies.radology.data.repositorty

import com.mosul_hospital.database.DatabaseFactory.dbQuery
import com.mosul_hospital.srevecies.radology.data.tables.RadiologyTable
import com.mosul_hospital.srevecies.radology.domain.entities.RadiologyInfo
import com.mosul_hospital.srevecies.radology.domain.mapper.toRadiology
import com.mosul_hospital.srevecies.radology.domain.usecase.RadiologyUseCase
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class RadiologyRepository: RadiologyUseCase {
    override suspend fun addNewRadiology(radiologyInfo: RadiologyInfo): Boolean {
        return dbQuery{
            val existingDoctor = RadiologyTable.selectAll()
                .where { RadiologyTable.radiologyId eq radiologyInfo.radiologyId }.singleOrNull()
            if (existingDoctor != null) {
                throw IllegalArgumentException("Radiology with id ${radiologyInfo.radiologyId} already exists.")
            }
            val insertStatement = RadiologyTable.insert {
                it[radiologyId] = radiologyInfo.radiologyId
                it[radiologyName] = radiologyInfo.radiologyName
                it[radiologyEmail] = radiologyInfo.radiologyEmail
            }
            insertStatement.insertedCount > 0
        }
    }

    override suspend fun deleteRadiology(radiologyId: String): Boolean {
        return dbQuery {
            RadiologyTable.deleteWhere { RadiologyTable.radiologyId eq  radiologyId} > 0
        }
    }

    override suspend fun updateRadiologyInfo(radiologyInfo: RadiologyInfo): Boolean {
        return dbQuery {
            RadiologyTable.update ({ RadiologyTable.radiologyId eq radiologyInfo.radiologyId }){
               it[radiologyName] = radiologyInfo.radiologyName
               it[radiologyEmail] = radiologyInfo.radiologyEmail
            } > 0
        }
    }

    override suspend fun getAllRadiology(): List<RadiologyInfo> {
        return dbQuery {
            try {
                RadiologyTable.selectAll().map { row ->
                    toRadiology(row)
                }
            }catch (e: Exception){
                throw e
            }
        }
    }
}

val radiologyRepo = RadiologyRepository()