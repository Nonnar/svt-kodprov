package org.example.svtkodprov.repository

import org.example.svtkodprov.entity.RadioProgram
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface RadioProgramRepository: JpaRepository<RadioProgram, Long> {

    fun findAllByProgramId(@Param("programId") programId: Long): List<RadioProgram>

    fun findAllByName(@Param("name") name: String): List<RadioProgram>

}





