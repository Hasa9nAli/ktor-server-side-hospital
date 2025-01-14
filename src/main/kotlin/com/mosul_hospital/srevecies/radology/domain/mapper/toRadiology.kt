package com.mosul_hospital.srevecies.radology.domain.mapper

import com.mosul_hospital.srevecies.radology.data.tables.RadiologyTable
import com.mosul_hospital.srevecies.radology.domain.entities.RadiologyInfo
import org.jetbrains.exposed.sql.ResultRow

fun toRadiology(row: ResultRow): RadiologyInfo{
    return RadiologyInfo(
        radiologyId = row[RadiologyTable.radiologyId],
        radiologyEmail = row[RadiologyTable.radiologyEmail],
        radiologyName = row[RadiologyTable.radiologyName]
    )
}