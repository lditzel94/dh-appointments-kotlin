package com.digitalhouse.appointments.dentist.adapter.db

import com.digitalhouse.appointments.dentist.adapter.db.entity.DentistEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DentistJpaRepository : JpaRepository<DentistEntity, Long> {
    fun findByLicense(license: Long): DentistEntity
    fun deleteByLicense(license: Long): Long
}