package org.example.svtkodprov.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class RadioProgram(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val programId: Long,

    @Column
    var name: String,

    @Column(columnDefinition = "text")
    var description: String,

    )