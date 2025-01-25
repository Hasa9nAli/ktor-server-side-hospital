package com.mosul_hospital.srevecies.pharmacy.domain.mapper

import com.mosul_hospital.srevecies.pharmacy.data.tables.PharmacyTable
import com.mosul_hospital.srevecies.pharmacy.domain.entities.PharmacyInfo
import org.jetbrains.exposed.sql.ResultRow

fun toPharmacy(row: ResultRow): PharmacyInfo {
    return PharmacyInfo(
        pharmacyId = row[PharmacyTable.pharmacyEmployeeID],
        pharmacyName = row[PharmacyTable.pharmacyEmployeeName],
        pharmacyEmail = row[PharmacyTable.pharmacyEmployeeEmail]
    )

}