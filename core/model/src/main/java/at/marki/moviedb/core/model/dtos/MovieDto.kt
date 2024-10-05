package at.marki.moviedb.core.model.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    @SerialName("rating")
    val rating: Double,

    @SerialName("id")
    val id: Long,

    @SerialName("revenue")
    val revenue: Long? = null,

    @SerialName("releaseDate")
    val releaseDate: String? = null,

    @SerialName("director")
    val director: DirectorDto? = null,

    @SerialName("posterUrl")
    val posterUrl: String? = null,

    @SerialName("cast")
    val cast: List<CastDto>,

    @SerialName("runtime")
    val runtime: Int? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("reviews")
    val reviews: Int? = null,

    @SerialName("budget")
    val budget: Long? = null,

    @SerialName("language")
    val language: String? = null,

    @SerialName("genres")
    val genres: List<String>
)
