package at.marki.moviedb.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import at.marki.moviedb.core.database.model.CastEntity

@Dao
interface CastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCast(cast: CastEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCasts(casts: List<CastEntity>)

    @Query("SELECT * FROM casts WHERE movie_id = :movieId")
    suspend fun getCastByMovieId(movieId: Long): List<CastEntity>

    @Query("DELETE FROM casts WHERE id = :id")
    suspend fun deleteCastById(id: Long)
}
