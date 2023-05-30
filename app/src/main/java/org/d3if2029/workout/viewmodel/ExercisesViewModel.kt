package org.d3if2029.workout.viewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import org.d3if2029.workout.db.ExerciseDatabase
import org.d3if2029.workout.domain.ExerciseRepository
import org.d3if2029.workout.entity.Exercise

class ExercisesViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository: ExerciseRepository
    val difficulty = MutableStateFlow("all")

    init {
       val exerciseDao = ExerciseDatabase
           .getDatabase(application, viewModelScope, application.resources)
           .exerciseDao()
        repository = ExerciseRepository(exerciseDao)
    }

     val selectedExercise = MutableLiveData<Exercise>()

    fun setSelectedExercise(exercise: Exercise){
        selectedExercise.value = exercise
    }

    fun getAllExercises(): LiveData<List<Exercise>>{
        return repository.getAllExercises()
    }

    val exercises = difficulty.flatMapLatest {
        repository.getAllOrSearch(it)
    }.asLiveData()

    fun getExercisesByDifficulty(difficulty: String): LiveData<List<Exercise>>{
        return repository.getExercisesByDifficulty(difficulty)
    }


}