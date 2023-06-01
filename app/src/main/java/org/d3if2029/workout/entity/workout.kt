package org.d3if2029.workout.entity

import java.sql.Date

data class Workout(
    val date: Date,
    val type: String,
    val duration: Int,
    val repetitions: Int,
    val sets: Int
)