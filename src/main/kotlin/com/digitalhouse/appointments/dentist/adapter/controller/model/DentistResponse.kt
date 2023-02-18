package com.digitalhouse.appointments.dentist.adapter.controller.model

import com.digitalhouse.appointments.dentist.domain.Dentist

class DentistResponse(
    val license: Long,
    val lastName: String,
    val firstName: String,
) {
    companion object {
        fun from(dentist: Dentist) =
            with(dentist) {
                DentistResponse(
                    license = license,
                    lastName = lastName,
                    firstName = firstName
                )
            }
    }
}