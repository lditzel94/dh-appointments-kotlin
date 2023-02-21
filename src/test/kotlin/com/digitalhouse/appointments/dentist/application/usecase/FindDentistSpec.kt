package com.digitalhouse.appointments.dentist.application.usecase

import arrow.core.left
import arrow.core.right
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

class FindDentistSpec : FeatureSpec({

    val repository = mockk<DentistRepositoryOutPort>()

    val useCase = FindDentist(
        repository = repository
    )

    beforeEach { clearAllMocks() }

    feature("find dentist") {
        scenario("success") {
            val license: Long = 123456
            val dentist = Dentist(
                license = license, lastName = "Ditzel", firstName = "Luciano"
            )

            every { repository.findBy(license) } returns dentist.right()

            useCase.execute(license) shouldBeRight dentist

            verify(exactly = 1) { repository.findBy(license) }
        }

        scenario("fail") {
            val license: Long = 123456

            val error = ApplicationErrorException()

            every { repository.findBy(license) } returns error.left()

            useCase.execute(license) shouldBeLeft error

            verify(exactly = 1) { repository.findBy(license) }
        }
    }
})
