package org.d3if2029.workout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.d3if2029.workout.R
import org.d3if2029.workout.entity.Workout

class WorkoutAdapter(private val workouts: List<Workout>) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val typeTextView: TextView = view.findViewById(R.id.typeTextView)
        val durationTextView: TextView = view.findViewById(R.id.durationTextView)
        val repetitionsTextView: TextView = view.findViewById(R.id.repetitionsTextView)
        val setsTextView: TextView = view.findViewById(R.id.setsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workout = workouts[position]
        holder.dateTextView.text = workout.date.toString()
        holder.typeTextView.text = workout.type
        holder.durationTextView.text = workout.duration.toString()
        holder.repetitionsTextView.text = workout.repetitions.toString()
        holder.setsTextView.text = workout.sets.toString()
    }

    override fun getItemCount(): Int {
        return workouts.size
    }
}