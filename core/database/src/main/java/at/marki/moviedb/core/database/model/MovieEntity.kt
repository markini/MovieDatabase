package at.marki.moviedb.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import at.marki.moviedb.core.model.Cast
import at.marki.moviedb.core.model.Director
import at.marki.moviedb.core.model.Movie
import kotlinx.datetime.LocalDate

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "rating")
    val rating: Double?,

    @ColumnInfo(name = "revenue")
    val revenue: Long?,

    @ColumnInfo(name = "release_date")
    val releaseDate: LocalDate?,

    @ColumnInfo(name = "poster_url")
    val posterUrl: String?,

    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "reviews")
    val reviews: Int?,

    @ColumnInfo(name = "budget")
    val budget: Long?,

    @ColumnInfo(name = "language")
    val language: String?,

    @ColumnInfo(name = "genres")
    val genres: String?,
)

fun MovieEntity.toModel(
    director: Director?,
    cast: List<Cast>,
): Movie {
    return Movie(
        id = id,
        title = title,
        rating = rating,
        revenue = revenue,
        releaseDate = releaseDate,
        posterUrl = posterUrl,
        runtime = runtime,
        overview = overview,
        reviews = reviews,
        budget = budget,
        language = language,
        genres = genres?.split(",") ?: emptyList(),
        director = director,
        cast = cast,
    )
}
