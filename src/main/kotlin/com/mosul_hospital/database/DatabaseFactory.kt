package com.mosul_hospital.database

import com.mosul_hospital.srevecies.doctorUser.data.tables.DoctorInfoTable
import com.mosul_hospital.srevecies.doctorUser.data.tables.PatientCaseTable
import com.mosul_hospital.srevecies.receptionUser.data.tables.PatientsReceptionInfo
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            driver = "org.h2.Driver",
            user = "root",
            password = ""
        )
        transaction {
            SchemaUtils.create(PatientsReceptionInfo)
            SchemaUtils.create(DoctorInfoTable)
            SchemaUtils.create(PatientCaseTable)
        }
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}