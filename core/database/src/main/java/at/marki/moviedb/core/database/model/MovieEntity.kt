package at.marki.moviedb.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
