package com.digitalhouse.appointments.dentist.adapter.db

import arrow.core.Either
import arrow.core.Either.Companion.catch
import com.digitalhouse.appointments.dentist.adapter.db.entity.DentistEntity
import com.digitalhouse.appointments.dentist.application.port.out.DentistRepositoryOutPort
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException
import com.digitalhouse.appointments.shared.utils.CompanionLogger
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class DentistRepository(
    private val repository: DentistJpaRepository
) : DentistRepositoryOutPort {
    override fun save(dentist: Dentist): Either<ApplicationErrorException, Dentist> =
        catch {
            dentist
                .toEntity()
                .doSave()
                .toDentist()
        }.mapLeft { throw it }

    override fun findBy(license: Long): Either<ApplicationErrorException, Dentist> =
        catch {
            repository.findByLicense(license)
                .toDentist()
        }.mapLeft { throw it }

    @Transactional
    override fun deleteBy(license: Long): Either<ApplicationErrorException, Long> =
        catch {
            repository.deleteByLicense(license)
        }.mapLeft { throw it }


    private fun Dentist.toEntity() =
        DentistEntity.from(this)
            .log { info("DentistEntity provided: {}", it) }

    private fun DentistEntity.doSave() =
        repository.save(this)
            .log { info("DentistEntity saved: {}", it) }

    companion object : CompanionLogger()
}