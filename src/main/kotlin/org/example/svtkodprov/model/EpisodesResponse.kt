package org.example.svtkodprov.model

import java.util.*

data class EpisodesResponse(
    val copyright: String,
    val episode: Episode
)

data class Episode(
    val id: Int,
    val title: String,
    val description: String,
    val publishdateutc: Date,
)


