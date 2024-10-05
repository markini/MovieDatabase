package at.marki.moviedb.core.data.model

import at.marki.moviedb.core.database.model.DirectorEntity
import at.marki.moviedb.core.model.dtos.DirectorDto

fun DirectorDto.toEntity(movieId: Long): DirectorEntity = DirectorEntity(
    movieId = movieId,
    name = name,
    pictureUrl = pictureUrl,
)
