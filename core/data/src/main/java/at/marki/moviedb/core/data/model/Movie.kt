package at.marki.moviedb.core.data.model

import at.marki.moviedb.core.database.model.MovieEntity
import at.marki.moviedb.core.model.dtos.MovieDto
import kotlinx.datetime.LocalDate

fun MovieDto.toEntity(): MovieEntity = MovieEntity(
    id = id,
    title = title,
    rating = rating,
    revenue = revenue,
    releaseDate = releaseDate?.let { LocalDate.parse(it) },
    posterUrl = posterUrl,
    runtime = runtime,
    overview = overview,
    reviews = reviews,
    budget = budget,
    language = language,
    genres = genres.joinToString(","),
)
