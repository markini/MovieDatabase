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
    suspend fun getMovieById(id: Long): MovieWithCast?

    @Transaction
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieWithCast>>
}