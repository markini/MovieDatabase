package at.marki.moviedb.core.model.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    @SerialName("name")
    val name: String,

    @SerialName("pictureUrl")
    val pictureUrl: String,

    @SerialName("character")
    val character: String
)
