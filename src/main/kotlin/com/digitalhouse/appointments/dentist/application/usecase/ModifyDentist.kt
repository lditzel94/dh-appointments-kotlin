package com.digitalhouse.appointments.dentist.application.usecase

import arrow.core.Either
import com.digitalhouse.appointments.dentist.application.port.`in`.ModifyDentistInPort
import com.digitalhouse.appointments.dentist.application.port.out.DentistRepositoryOutPort
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.dentist.domain.DentistRequirement
import com.digitalhouse.appointments.dentist.domain.provider.UpdatedDentistProvider
import com.digitalhouse.appointments.shared.UseCase
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException
import com.digitalhouse.appointments.shared.utils.CompanionLogger

@UseCase
class ModifyDentist(
    private val repository: DentistRepositoryOutPort,
    private val updatedDentistProvider: UpdatedDentistProvider,
) : ModifyDentistInPort {
    override fun execute(old: Dentist, new: DentistRequirement): Either<ApplicationErrorException, Dentist> =
        old.updateWith(new)
            .save()

    private fun Dentist.updateWith(new: DentistRequirement) =
        updatedDentistProvider.provideFor(this, new)
            .log { info("Dentist updated: {}", it) }

    private fun Dentist.save() =
        repository.save(this)
            .logRight { info("Dentist saved updated: {}", it) }

    companion object : CompanionLogger()
}