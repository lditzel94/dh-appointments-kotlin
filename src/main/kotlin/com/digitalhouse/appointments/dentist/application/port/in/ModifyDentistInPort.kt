package com.digitalhouse.appointments.dentist.application.port.`in`

import arrow.core.Either
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.dentist.domain.DentistRequirement
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

interface ModifyDentistInPort {
    fun execute(old: Dentist, new: DentistRequirement): Either<ApplicationErrorException, Dentist>
}