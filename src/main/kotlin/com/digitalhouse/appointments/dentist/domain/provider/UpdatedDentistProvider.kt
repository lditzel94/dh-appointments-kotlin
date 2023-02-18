package com.digitalhouse.appointments.dentist.domain.provider

import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.dentist.domain.DentistRequirement
import com.digitalhouse.appointments.shared.Provider
import com.digitalhouse.appointments.shared.utils.CompanionLogger

@Provider
class UpdatedDentistProvider {

    fun provideFor(old: Dentist, new: DentistRequirement) =
        with(new) {
            old.copy(
                license = license ?: old.license,
                lastName = lastName ?: old.lastName,
                firstName = firstName ?: old.firstName
            )
        }.log { info("Updated Dentist provided: {}", it) }

    companion object : CompanionLogger()
}