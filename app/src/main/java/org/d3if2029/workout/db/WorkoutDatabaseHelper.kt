package org.d3if2029.workout.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.SimpleDateFormat
import org.d3if2029.workout.entity.Workout
import org.d3if2029.workout.ui.SecondFragment
import java.sql.Date

class WorkoutDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_WORKOUT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_WORKOUT)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "workout.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_WORKOUT = "workout"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_TYPE = "type"
        private const val COLUMN_DURATION = "duration"
        private const val COLUMN_REPETITIONS = "repetitions"
        private const val COLUMN_SETS = "sets"

        private const val CREATE_TABLE_WORKOUT = """
            CREATE TABLE $TABLE_WORKOUT (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DATE TEXT,
                $COLUMN_TYPE TEXT,
                $COLUMN_DURATION INTEGER,
                $COLUMN_REPETITIONS INTEGER,
                $COLUMN_SETS INTEGER
            )
        """

        private const val DROP_TABLE_WORKOUT = "DROP TABLE IF EXISTS $TABLE_WORKOUT"
    }

    fun addWorkout(workout: Workout) {
        val values = ContentValues().apply {
            put(COLUMN_DATE, workout.date.toString())
            put(COLUMN_TYPE, workout.type)
            put(COLUMN_DURATION, workout.duration)
            put(COLUMN_REPETITIONS, workout.repetitions)
            put(COLUMN_SETS, workout.sets)
        }

        writableDatabase.insert(TABLE_WORKOUT, null, values)
    }

    fun getAllWorkouts(): List<Workout> {
        val workouts = mutableListOf<Workout>()

        val cursor = readableDatabase.query(
            TABLE_WORKOUT,
            arrayOf(COLUMN_DATE, COLUMN_TYPE, COLUMN_DURATION, COLUMN_REPETITIONS, COLUMN_SETS),
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val date = SimpleDateFormat("yyyy-MM-dd").parse(getString(getColumnIndexOrThrow(COLUMN_DATE)))
                val type = getString(getColumnIndexOrThrow(COLUMN_TYPE))
                val duration = getInt(getColumnIndexOrThrow(COLUMN_DURATION))
                val repetitions = getInt(getColumnIndexOrThrow(COLUMN_REPETITIONS))
                val sets = getInt(getColumnIndexOrThrow(COLUMN_SETS))

                workouts.add(Workout(date as Date, type, duration, repetitions, sets))
            }
        }

        return workouts
    }
}