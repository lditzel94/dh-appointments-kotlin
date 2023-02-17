package com.digitalhouse.appointments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppointmentsApplication

fun main(args: Array<String>) {
    runApplication<AppointmentsApplication>(*args)
}
