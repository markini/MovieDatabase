package at.marki.moviedb.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import at.marki.moviedb.core.database.model.CastEntity
import at.marki.moviedb.core.database.model.DirectorEntity
import at.marki.moviedb.core.database.model.MovieEntity

data class MovieWithCast(
    @Embedded
    val movieEntity: MovieEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id",
    )
    val director: DirectorEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "movie_id",
    )
    val cast: List<CastEntity>,
)
