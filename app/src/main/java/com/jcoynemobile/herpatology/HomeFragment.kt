package com.jcoynemobile.herpatology

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jcoynemobile.herpatology.databinding.FragmentHomeBinding
import com.jcoynemobile.herpatology.EncyclopediaFragment

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var encyclopedia = EncyclopediaFragment()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_encyclopediaFragment)
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
        }

        binding.buttonThird.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_regionFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}