package com.mosul_hospital.srevecies.pharmacy.data.tables

import org.jetbrains.exposed.sql.Table

object PharmacyTable: Table() {
    val pharmacyEmployeeID  = varchar("pharmacyEmployeeId", 65)
    val pharmacyEmployeeName = varchar("pharmacyEmployeeName", 32)
    val pharmacyEmployeeEmail = varchar("pharmacyEmployeeEmail", 64)
}