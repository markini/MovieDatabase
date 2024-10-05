package at.marki.moviedb.core.data.model

import at.marki.moviedb.core.database.model.CastEntity
import at.marki.moviedb.core.model.dtos.CastDto

fun CastDto.toEntity(movieId: Long): CastEntity = CastEntity(
    movieId = movieId,
    name = name,
    pictureUrl = pictureUrl,
    character = character,
)

fun List<CastDto>.toEntities(
    movieId: Long,
) = this.map { it.toEntity(movieId = movieId) }