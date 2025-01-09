package com.mosul_hospital.srevecies.receptionUser.data.tables

import com.mosul_hospital.srevecies.receptionUser.data.model.PatientInitInfo
import org.jetbrains.exposed.sql.Table

// create table
object PatientsReceptionInfo: Table(){
    val patientId = varchar("patientId", 64)
    val patientFullName = varchar("patientFullName", 64)
    val patientMotherName = varchar("patientMotherName", 64)
    val patientAge = integer("patientAge")
    val patientPhoneNumber = varchar("patientPhoneNumber", 64)
    val gender = varchar("gender",12)
    val patientCompanionName = varchar("patientCompanionName", 64)
    val patientCompanionPhoneNumber = varchar("patientCompanionPhoneNumber", 64)
    val maritalStatus = varchar("maritalStatus", 64)
    val isHaveAllergyToTreatment = bool("isHaveAllergyToTreatment")
    val job = varchar("job", 64)
    val isHavSurgeryHistory = bool("isHavSurgeryHistory")
    val previousSurgeryHistory = varchar("previousSurgeryHistory", 64)
    val howArriveToHospital = varchar("howArriveToHospital", 64)
    val doctorName = varchar("doctorName", 64)
    val bloodType = varchar("bloodType", 64)

    override val primaryKey: PrimaryKey = PrimaryKey(patientId)
}

// mapper function
fun toPatientReceptionInfo(row: org.jetbrains.exposed.sql.ResultRow): PatientInitInfo =
    PatientInitInfo(
        patientId = row[PatientsReceptionInfo.patientId],
        patientFullName = row[PatientsReceptionInfo.patientFullName],
        patientMotherName = row[PatientsReceptionInfo.patientMotherName],
        patientAge = row[PatientsReceptionInfo.patientAge],
        patientPhoneNumber = row[PatientsReceptionInfo.patientPhoneNumber],
        patientGender = row[PatientsReceptionInfo.gender],
        patientCompanionName = row[PatientsReceptionInfo.patientCompanionName],
        patientCompanionPhoneNumber = row[PatientsReceptionInfo.patientCompanionPhoneNumber],
        maritalStatus = row[PatientsReceptionInfo.maritalStatus],
        previousSurgeryHistory = row[PatientsReceptionInfo.previousSurgeryHistory],
        bloodType = row[PatientsReceptionInfo.bloodType],
        job = row[PatientsReceptionInfo.job],
        doctorName = row[PatientsReceptionInfo.doctorName],
        isHaveAllergyToTreatment = row[PatientsReceptionInfo.isHaveAllergyToTreatment],
        isHavSurgeryHistory = row[PatientsReceptionInfo.isHavSurgeryHistory],
        howArriveToHospital = row[PatientsReceptionInfo.howArriveToHospital]
    )
