package at.marki.moviedb.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "casts")
data class CastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "picture_url")
    val pictureUrl: String?,

    @ColumnInfo(name = "character")
    val character: String?,

    @ColumnInfo(name = "movie_id")
    val movieId: Long,
)
