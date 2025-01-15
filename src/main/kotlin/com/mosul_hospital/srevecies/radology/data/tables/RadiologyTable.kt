package com.mosul_hospital.srevecies.radology.data.tables

import org.jetbrains.exposed.sql.Table

object RadiologyTable: Table() {
    val radiologyId = varchar("radiologyId", 64)
    val radiologyEmail = varchar("radiologyEmail", 128)
    val radiologyName = varchar("radiologyName", 32)

    override val primaryKey =  PrimaryKey(radiologyId)
}