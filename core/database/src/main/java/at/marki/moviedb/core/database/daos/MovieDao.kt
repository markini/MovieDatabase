package at.marki.moviedb.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import at.marki.moviedb.core.database.model.MovieEntity
import at.marki.moviedb.core.database.relations.MovieWithCast
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Long): Flow<MovieWithCast?>

    @Transaction
    @Query("SELECT * FROM movies WHERE id IN (:ids)")
    fun getMoviesByIds(ids: List<Long>): Flow<List<MovieWithCast>>

    @Transaction
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieWithCast>>

    @Query(
        """
            SELECT *
            FROM movies
            WHERE
                title LIKE '%' || :query || '%' 
            ORDER BY
                CASE
                    WHEN title LIKE :query || '%' THEN 1
                    ELSE 2
                END,
                title
        """
    )
    fun searchMovies(query: String): Flow<List<MovieWithCast>>
}