package com.digitalhouse.appointments.dentist.application.usecase

import arrow.core.Either
import com.digitalhouse.appointments.dentist.adapter.controller.DentistController.Companion.logRight
import com.digitalhouse.appointments.dentist.application.port.`in`.FindDentistInPort
import com.digitalhouse.appointments.dentist.application.port.out.DentistRepositoryOutPort
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.shared.UseCase
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

@UseCase
class FindDentist(
    private val repository: DentistRepositoryOutPort
) : FindDentistInPort {

    override fun execute(license: Long): Either<ApplicationErrorException, Dentist> =
        repository.findBy(license)
            .logRight { info("Dentist found: {}", it) }
}