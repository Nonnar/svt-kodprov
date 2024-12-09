package org.example.svtkodprov.model

data class Pagination(
val page: Int,
val size: Int,
val totalHits: Int,
val totalPages: Int,
var nextPage: String?,
)

