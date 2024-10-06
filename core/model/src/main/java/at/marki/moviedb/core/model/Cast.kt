package at.marki.moviedb.core.model

data class Cast(
    val id: Long,
    val name: String?,
    val pictureUrl: String?,
    val character: String?,
    val movieId: Long
)
