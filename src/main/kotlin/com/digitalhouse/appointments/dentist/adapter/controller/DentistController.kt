package com.digitalhouse.appointments.dentist.adapter.controller

import arrow.core.flatMap
import com.digitalhouse.appointments.dentist.adapter.controller.model.DentistRequest
import com.digitalhouse.appointments.dentist.adapter.controller.model.DentistResponse
import com.digitalhouse.appointments.dentist.adapter.controller.model.OptionalDentistRequest
import com.digitalhouse.appointments.dentist.application.port.`in`.CreateDentistInPort
import com.digitalhouse.appointments.dentist.application.port.`in`.DeleteDentistInPort
import com.digitalhouse.appointments.dentist.application.port.`in`.FindDentistInPort
import com.digitalhouse.appointments.dentist.application.port.`in`.ModifyDentistInPort
import com.digitalhouse.appointments.dentist.domain.Dentist
import com.digitalhouse.appointments.dentist.domain.DentistRequirement
import com.digitalhouse.appointments.shared.throwIfLeft
import com.digitalhouse.appointments.shared.utils.CompanionLogger
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dentists")
class DentistController(
    private val create: CreateDentistInPort,
    private val find: FindDentistInPort,
    private val edit: ModifyDentistInPort,
    private val delete: DeleteDentistInPort
) {

    @GetMapping("/{license}")
    fun find(@PathVariable license: Long): DentistResponse =
        license
            .doFind()
            .map { it.toResponse() }
            .throwIfLeft()

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@Valid @RequestBody request: DentistRequest): DentistResponse =
        request
            .toDomain()
            .doCreate()
            .map { it.toResponse() }
            .throwIfLeft()

    @PatchMapping("/{license}")
    fun modify(
        @PathVariable license: Long,
        @RequestBody new: OptionalDentistRequest
    ): DentistResponse =
        license.doFind().flatMap { old ->
            old.editWith(new.toRequirement())
                .map { it.toResponse() }
        }.throwIfLeft()

    @DeleteMapping("/{license}")
    fun delete(@PathVariable license: Long): Long =
        with(license) {
            doDelete()
                .map { this }
                .throwIfLeft()
        }

    private fun Long.doFind() =
        find.execute(this)
            .logRight { info("Dentist found: {}", it) }

    private fun Dentist.doCreate() =
        create.execute(this)
            .logRight { info("Dentist created: {}", it) }

    private fun Dentist.editWith(new: DentistRequirement) =
        edit.execute(
            old = this,
            new = new
        ).logRight { info("Dentist edited: {}", it) }

    private fun Long.doDelete() =
        delete.execute(this)
            .logRight { info("Dentist deleted: {}", it) }

    private fun Dentist.toResponse() =
        DentistResponse.from(this)

    companion object : CompanionLogger()
}