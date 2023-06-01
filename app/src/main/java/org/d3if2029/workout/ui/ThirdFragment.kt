package org.d3if2029.workout.ui


import android.content.Context
import android.os.Bundle
import android.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.d3if2029.workout.R
import org.d3if2029.workout.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private lateinit var pref: Preference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        val contextt: Context
        contextt = requireActivity()
        pref = Preference(contextt)

        binding.constraintLayout4.setOnClickListener {
            it.findNavController().navigate(R.id.action_thirdFragment_to_editProfileFragment)
        }
        binding.constraintLayout5.setOnClickListener {
            it.findNavController().navigate(R.id.action_thirdFragment_to_gantiPasswordPerusahaanFragment)
        }
        return binding.root
    }
}