package at.marki.moviedb.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import at.marki.moviedb.core.database.model.CastEntity
import at.marki.moviedb.core.database.model.DirectorEntity
import at.marki.moviedb.core.database.model.MovieEntity
import at.marki.moviedb.core.database.model.toModel
import at.marki.moviedb.core.database.model.toModels
import at.marki.moviedb.core.model.Movie

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

fun MovieWithCast.toModel(): Movie {
    val cast = cast.toModels()
    val director = director.toModel()
    return movieEntity.toModel(director, cast)
}
