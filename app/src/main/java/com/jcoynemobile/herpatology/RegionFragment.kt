package com.jcoynemobile.herpatology

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.jcoynemobile.herpatology.databinding.FragmentRegionBinding
import kotlinx.coroutines.launch

class RegionFragment : Fragment() {


    private var _binding: FragmentRegionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegionBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_regionFragment_to_encyclopediaFragment_ne)
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_regionFragment_to_encyclopediaFragment_nw)
        }

        binding.buttonThird.setOnClickListener {
            findNavController().navigate(R.id.action_regionFragment_to_encyclopediaFragment_c)
        }

        binding.buttonFourth.setOnClickListener {
            findNavController().navigate(R.id.action_regionFragment_to_encyclopediaFragment_se)
        }

        binding.buttonFifth.setOnClickListener {
            findNavController().navigate(R.id.action_regionFragment_to_encyclopediaFragment_sw)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}