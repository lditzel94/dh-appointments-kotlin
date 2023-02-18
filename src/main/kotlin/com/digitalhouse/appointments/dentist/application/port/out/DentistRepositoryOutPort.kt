package com.digitalhouse.appointments.dentist.application.port.out

import arrow.core.Either
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

interface DentistRepositoryOutPort {
    fun save(dentist: Dentist): Either<ApplicationErrorException, Dentist>
    fun findBy(license: Long): Either<ApplicationErrorException, Dentist>
    fun deleteBy(license: Long): Either<ApplicationErrorException, Long>
}