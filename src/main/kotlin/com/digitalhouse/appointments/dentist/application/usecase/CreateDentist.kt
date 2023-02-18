package com.digitalhouse.appointments.dentist.application.usecase

import arrow.core.Either
import com.digitalhouse.appointments.dentist.adapter.controller.DentistController.Companion.logRight
import com.digitalhouse.appointments.dentist.application.port.`in`.CreateDentistInPort
import com.digitalhouse.appointments.dentist.application.port.out.DentistRepositoryOutPort
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.shared.UseCase
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

@UseCase
class CreateDentist(
    private val repository: DentistRepositoryOutPort
) : CreateDentistInPort {

    override fun execute(dentist: Dentist): Either<ApplicationErrorException, Dentist> =
        repository.save(dentist)
            .logRight { info("Dentist saved: {}", it) }
}