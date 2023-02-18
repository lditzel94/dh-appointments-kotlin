package com.digitalhouse.appointments.shared

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

@Target(CLASS)
@Retention(RUNTIME)
@MustBeDocumented
@Component
annotation class UseCase()

@Target(CLASS)
@Retention(RUNTIME)
@MustBeDocumented
@Component
annotation class Provider()

@Target(CLASS)
@Retention(RUNTIME)
@MustBeDocumented
@Component
@Primary
annotation class MockedAdapter()