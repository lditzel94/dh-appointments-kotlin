package com.digitalhouse.appointments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppointmentsKotlinApplication

fun main(args: Array<String>) {
    runApplication<AppointmentsKotlinApplication>(*args)
}
