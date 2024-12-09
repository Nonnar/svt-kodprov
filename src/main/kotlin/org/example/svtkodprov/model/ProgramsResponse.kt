package org.example.svtkodprov.model

data class ProgramsResponse(
    val copyright: String,
    val pagination: Pagination,
    val programs: Programs
)

data class Programs(
    val program: List<Program>
)

data class Program(
    val id: String,
    val name: String,
    val description: String
)

