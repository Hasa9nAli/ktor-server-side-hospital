package com.mosul_hospital.srevecies.doctorUser.domain

data class PatientInitInfo(
    val patientId : String,
    val patientFullName : String,
    val patientMotherName : String,
    val patientAge : Int,
    val patientPhoneNumber:String,
    val patientGender: String,
    val patientCompanionName: String,
    val patientCompanionPhoneNumber: String,
    val maritalStatus: String,
    val bloodType: String,
    val job: String,
    val howArriveToHospital: String,
    val previousSurgeryHistory: String,
    val doctorName: String,
    val isHaveAllergyToTreatment: Boolean,
    val isHavSurgeryHistory: Boolean
)
