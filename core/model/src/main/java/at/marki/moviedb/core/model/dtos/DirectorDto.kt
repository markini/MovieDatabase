package at.marki.moviedb.core.model.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DirectorDto(
    @SerialName("name")
    val name: String,

    @SerialName("pictureUrl")
    val pictureUrl: String
)
