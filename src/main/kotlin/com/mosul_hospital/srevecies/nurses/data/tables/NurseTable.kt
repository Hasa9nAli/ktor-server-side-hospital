package com.mosul_hospital.srevecies.nurses.data.tables

import org.jetbrains.exposed.sql.Table

object NurseTable: Table() {
    val nurseId  = varchar("nurseId", 65)
    val nurseName = varchar("nurseName", 32)
    val nurseSpecification = varchar("nurseSpecification", 64)
}