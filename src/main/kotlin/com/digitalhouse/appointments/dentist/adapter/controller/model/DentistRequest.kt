package com.digitalhouse.appointments.dentist.adapter.controller.model

import com.digitalhouse.appointments.dentist.domain.Dentist

data class DentistRequest(
    val license: Long,
    val lastName: String,
    val firstName: String,
) {
    fun toDomain() =
        Dentist(
            license = license,
            lastName = lastName,
            firstName = firstName
        )
}
