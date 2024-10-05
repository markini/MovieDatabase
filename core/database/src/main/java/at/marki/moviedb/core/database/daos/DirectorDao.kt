package at.marki.moviedb.core.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import at.marki.moviedb.core.database.model.DirectorEntity

@Dao
interface DirectorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: DirectorEntity)

    @Query("SELECT * FROM directors WHERE movie_id = :movieId")
    suspend fun getDirectorByMovieId(movieId: Long): DirectorEntity?

    @Query("DELETE FROM directors WHERE id = :id")
    suspend fun deleteDirectorById(id: Long)
}
