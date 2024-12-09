package org.example.svtkodprov.model

import java.util.*

data class EpisodesResponse(
    val copyright: String,
    val pagination: Pagination,
    val episodes: Episodes
)

data class Episodes(
    val episode: List<Episode>
)

data class Episode(
    val id: Int,
    val title: String,
    val description: String,
    val publishdateutc: Date,
)


