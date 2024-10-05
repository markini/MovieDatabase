package at.marki.moviedb.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "directors")
data class DirectorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "picture_url")
    val pictureUrl: String?,

    @ColumnInfo(name = "movie_id")
    val movieId: Long,
)
