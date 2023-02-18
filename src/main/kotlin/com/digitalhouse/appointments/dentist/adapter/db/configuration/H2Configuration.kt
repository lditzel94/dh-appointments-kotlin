package com.digitalhouse.appointments.dentist.adapter.db.configuration

import com.digitalhouse.appointments.dentist.adapter.db.DentistJpaRepository
import com.digitalhouse.appointments.dentist.adapter.db.entity.DentistEntity
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class H2Configuration {

    @Bean
    fun dbInitializer(repository: DentistJpaRepository) = ApplicationRunner {
        repository.save(
            DentistEntity(
                license = 333333,
                lastName = "Ditzel",
                firstName = "Luciano"
            )
        )
    }
}