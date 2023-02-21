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

class CreateDentistSpec : FeatureSpec({

    val repository = mockk<DentistRepositoryOutPort>()

    val useCase = CreateDentist(
        repository = repository
    )

    beforeEach { clearAllMocks() }

    feature("create dentist") {
        scenario("success") {
            val dentist = Dentist(
                license = 123456, lastName = "Ditzel", firstName = "Luciano"
            )

            every { repository.save(dentist) } returns dentist.right()

            useCase.execute(dentist) shouldBeRight dentist

            verify(exactly = 1) { repository.save(dentist) }
        }

        scenario("fail") {
            val dentist = Dentist(
                license = 123456, lastName = "Ditzel", firstName = "Luciano"
            )

            val error = ApplicationErrorException()

            every { repository.save(dentist) } returns error.left()

            useCase.execute(dentist) shouldBeLeft error

            verify(exactly = 1) { repository.save(dentist) }
        }
    }

})
