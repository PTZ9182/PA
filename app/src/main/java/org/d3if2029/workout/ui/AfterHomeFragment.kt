package org.d3if2029.workout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import org.d3if2029.workout.R
import org.d3if2029.workout.adapter.ExerciseAdapter
import org.d3if2029.workout.databinding.FragmentAfterHomeBinding
import org.d3if2029.workout.databinding.FragmentHomeBinding
import org.d3if2029.workout.viewmodel.ExercisesViewModel

class AfterHomeFragment : Fragment(){
    private lateinit var _binding: FragmentAfterHomeBinding
    private lateinit var exerciseViewModel: ExercisesViewModel
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAfterHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.let {
            exerciseViewModel = ViewModelProvider(it!!).get(ExercisesViewModel::class.java)
        }
        exerciseAdapter = ExerciseAdapter(mutableListOf())
        exerciseAdapter.setOnExerciseListener { exercise ->
            exerciseViewModel.setSelectedExercise(exercise)
            findNavController().navigate(R.id.timerActivity, arguments, NavOptions.Builder().setPopUpTo(R.id.timerActivity, true).build())
        }
        attachObservers()
    }
    private fun attachObservers() {
        exerciseViewModel.exercises.observe(viewLifecycleOwner) {
            _binding.rvExercises.apply {
                adapter = exerciseAdapter
                layoutManager = LinearLayoutManager(activity)
            }
            exerciseAdapter.showData(it)
        }
    }


}