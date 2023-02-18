package com.digitalhouse.appointments.dentist.adapter.db

import arrow.core.Either
import arrow.core.right
import com.digitalhouse.appointments.dentist.application.port.out.DentistRepositoryOutPort
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.shared.MockedAdapter
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Profile

@MockedAdapter
@Profile("local")
@ConditionalOnProperty(value = ["mock.dentist.repository"], havingValue = "true")
class MockDentistRepository : DentistRepositoryOutPort {

    override fun save(dentist: Dentist): Either<ApplicationErrorException, Dentist> =
        dentist.right()

    override fun findBy(license: Long): Either<ApplicationErrorException, Dentist> =
        Dentist(
            license = license,
            lastName = "Ditzel",
            firstName = "Luciano"
        ).right()


    override fun deleteBy(license: Long): Either<ApplicationErrorException, Long> =
        license.right()
}