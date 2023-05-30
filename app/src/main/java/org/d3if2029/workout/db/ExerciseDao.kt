package org.d3if2029.workout.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.d3if2029.workout.entity.Exercise


@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAllExercises(exercise: List<Exercise>)

    @Query("SELECT * FROM exercise")
    fun getExercises(): LiveData<List<Exercise>>
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise")
    fun getExercisesList(): List<Exercise>

    @Query("Select * from exercise WHERE difficultyLevel=:difficulty")
    fun getDifficulty(difficulty: String?): LiveData<List<Exercise>>

    @Query("Select * from exercise WHERE difficultyLevel=:difficulty")
    fun getByDifficulty(difficulty: String?): Flow<List<Exercise>>


}