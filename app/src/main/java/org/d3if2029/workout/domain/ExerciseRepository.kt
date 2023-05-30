package org.d3if2029.workout.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import org.d3if2029.workout.db.ExerciseDao
import org.d3if2029.workout.entity.Exercise


class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): LiveData<List<Exercise>> {
        return exerciseDao.getExercises()
    }
    fun getExercisesByDifficulty(difficulty: String): LiveData<List<Exercise>>{
        return exerciseDao.getDifficulty(difficulty)
    }

    fun getAllOrSearch(difficulty: String): Flow<List<Exercise>> {
        return if (difficulty == "all") {
            exerciseDao.getAllExercises()
        } else {
            exerciseDao.getByDifficulty(difficulty)
        }
    }
}