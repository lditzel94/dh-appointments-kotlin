package com.digitalhouse.appointments.dentist.adapter.controller.model

import com.digitalhouse.appointments.dentist.domain.DentistRequirement

data class OptionalDentistRequest(
    val license: Long?,
    val lastName: String?,
    val firstName: String?,
) {

    fun toRequirement() =
        DentistRequirement(
            license = license,
            lastName = lastName,
            firstName = firstName
        )
}