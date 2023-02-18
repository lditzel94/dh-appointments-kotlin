package com.digitalhouse.appointments.dentist.application.usecase

import arrow.core.Either
import com.digitalhouse.appointments.dentist.adapter.controller.DentistController.Companion.logRight
import com.digitalhouse.appointments.dentist.application.port.`in`.DeleteDentistInPort
import com.digitalhouse.appointments.dentist.application.port.out.DentistRepositoryOutPort
import com.digitalhouse.appointments.shared.UseCase
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

@UseCase
class DeleteDentist(
    private val repository: DentistRepositoryOutPort
) : DeleteDentistInPort {
    override fun execute(license: Long): Either<ApplicationErrorException, Long> =
        repository.deleteBy(license)
            .logRight { info("Dentist deleted: {}", it) }
}