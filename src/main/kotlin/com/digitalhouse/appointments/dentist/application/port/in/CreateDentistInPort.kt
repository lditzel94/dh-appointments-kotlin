package com.digitalhouse.appointments.dentist.application.port.`in`

import arrow.core.Either
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

interface CreateDentistInPort {
    fun execute(dentist: Dentist): Either<ApplicationErrorException, Dentist>
}