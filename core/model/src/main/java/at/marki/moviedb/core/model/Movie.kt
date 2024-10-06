package at.marki.moviedb.core.model

import kotlinx.datetime.LocalDate

data class Movie(
    val id: Long,
    val title: String?,
    val rating: Double?,
    val revenue: Long?,
    val releaseDate: LocalDate?,
    val posterUrl: String?,
    val runtime: Int?,
    val overview: String?,
    val reviews: Int?,
    val budget: Long?,
    val language: String?,
    val genres: List<String>,
    val director: Director?,
    val cast: List<Cast>,
)
