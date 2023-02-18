package com.digitalhouse.appointments.dentist.adapter.db.entity

import com.digitalhouse.appointments.dentist.domain.Dentist
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "DENTIST")
class DentistEntity(
    @Id
    var license: Long,
    var lastName: String,
    var firstName: String,
) {

    fun toDentist() =
        Dentist(
            license = license,
            lastName = lastName,
            firstName = firstName
        )

    companion object {
        fun from(dentist: Dentist) =
            with(dentist) {
                DentistEntity(
                    license = license,
                    lastName = lastName,
                    firstName = firstName
                )
            }
    }
}