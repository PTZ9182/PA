package org.d3if2029.workout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.d3if2029.workout.R
import org.d3if2029.workout.adapter.WorkoutAdapter
import org.d3if2029.workout.databinding.FragmentSecondBinding
import org.d3if2029.workout.db.WorkoutDatabaseHelper


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var workoutDatabaseHelper: WorkoutDatabaseHelper
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(layoutInflater)

        workoutDatabaseHelper = WorkoutDatabaseHelper(requireContext())
        val workouts = workoutDatabaseHelper.getAllWorkouts()

        workoutAdapter = WorkoutAdapter(workouts)
        binding.workoutRecyclerView.adapter = workoutAdapter

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_addWorkoutActivity2)
        }
        return binding.root
    }
}