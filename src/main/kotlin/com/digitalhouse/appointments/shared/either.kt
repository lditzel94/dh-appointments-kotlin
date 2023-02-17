package com.digitalhouse.appointments.shared

import arrow.core.Either
import com.digitalhouse.appointments.shared.adapter.controller.model.ApplicationErrorException

fun <R> Either<ApplicationErrorException, R>.throwIfLeft(): R =
    fold(
        ifLeft = { throw it },
        ifRight = { it }
    )