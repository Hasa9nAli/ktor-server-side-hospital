package com.mosul_hospital.srevecies.doctorUser.data.tables

import org.jetbrains.exposed.sql.Table

object DoctorInfoTable : Table() {

        val doctorId = varchar("doctorId", 50)
        val doctorName = varchar("doctorName", 100)
        val doctorSpecification = varchar("doctorSpecification", 100)


    override val primaryKey = PrimaryKey(doctorId)

}