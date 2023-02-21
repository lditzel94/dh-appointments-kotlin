package com.digitalhouse.appointments.dentist.application.usecase

import arrow.core.Either
import arrow.core.left
import com.digitalhouse.appointments.dentist.application.port.out.DentistRepositoryOutPort
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FeatureSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class DeleteDentistSpec : FeatureSpec({

    val repository = mockk<DentistRepositoryOutPort>()

    val useCase = DeleteDentist(
        repository = repository
    )

    beforeEach { clearAllMocks() }

    feature("delete dentist") {
        scenario("success") {
            val license: Long = 123456
            val dentist = Dentist(
                license = license, lastName = "Ditzel", firstName = "Luciano"
            )

            every { repository.deleteBy(license) } returns Either.Right(1)

            useCase.execute(license) shouldBeRight 1L

            verify(exactly = 1) { repository.deleteBy(license) }
        }


        scenario("fail") {
            val license: Long = 123456
            val dentist = Dentist(
                license = 123456, lastName = "Ditzel", firstName = "Luciano"
            )

            val error = ApplicationErrorException()

            every { repository.deleteBy(license) } returns error.left()

            useCase.execute(license) shouldBeLeft error

            verify(exactly = 1) { repository.deleteBy(license) }
        }
    }
})
