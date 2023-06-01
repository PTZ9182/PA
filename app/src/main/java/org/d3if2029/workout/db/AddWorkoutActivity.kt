package org.d3if2029.workout.db

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.d3if2029.workout.databinding.ActivityAddWorkoutBinding
import org.d3if2029.workout.entity.Workout
import java.util.*


class AddWorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddWorkoutBinding
    private lateinit var workoutDatabaseHelper: WorkoutDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workoutDatabaseHelper = WorkoutDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val date = Date()
            val type = binding.typeEditText.text.toString()
            val duration = binding.durationEditText.text.toString().toInt()
            val repetitions = binding.repetitionsEditText.text.toString().toInt()
            val sets = binding.setsEditText.text.toString().toInt()

            val workout = Workout(date as java.sql.Date, type, duration, repetitions, sets)
            workoutDatabaseHelper.addWorkout(workout)

            finish()
        }
    }
}