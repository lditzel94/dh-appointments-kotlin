package com.digitalhouse.appointments.patient.domain

import java.time.LocalDate

data class Patient(
    val firstName: String,
    val lastName: String,
    val address: String,
    val identity: Int,
    val createdDate: LocalDate,
)