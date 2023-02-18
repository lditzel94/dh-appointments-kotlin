package com.digitalhouse.appointments.dentist.application.port.`in`

import arrow.core.Either
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

interface DeleteDentistInPort {
    fun execute(license: Long): Either<ApplicationErrorException, Long>
}