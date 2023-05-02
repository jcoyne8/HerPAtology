package com.jcoynemobile.herpatology

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.jcoynemobile.herpatology.databinding.FragmentEncyclopediaBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class EncyclopediaFragment : Fragment() {

    private var _binding: FragmentEncyclopediaBinding? = null

    private val viewModel: EncyclopediaViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentEncyclopediaBinding.inflate(inflater, container, false)
        return binding.root

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = EncyclopediaFragmentArgs.fromBundle(requireArguments())
        val encyclopediaName = args.encyclopediaName
        val encyclopediaView = view.findViewById<TextView>(R.id.encyclopedia_name)
        encyclopediaView.setText(encyclopediaName)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val snakes = getSnakeRegion(setI(requireArguments().getInt("listType", 0)))
                val snakeLocation = viewModel.loadSnakes()
                binding.encyclopediaRecyclerView.adapter = EncyclopediaAdapter(snakes) { snakeId ->
                    val snakeImage = viewModel.getSnakeSource(snakeId)
                    findNavController().navigate(
                        EncyclopediaFragmentDirections.actionEncyclopediaFragmentToSpeciesFragment((snakeId - 1), snakeImage, snakeLocation[snakeId-1].name, snakeLocation[snakeId-1].scientificName, snakeLocation[snakeId-1].venom, snakeLocation[snakeId-1].habitat, snakeLocation[snakeId-1].length, snakeLocation[snakeId-1].range, snakeLocation[snakeId-1].color, snakeLocation[snakeId-1].snakeNotes))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    suspend fun getSnakeRegion(regionNum: Int): List<Snake> {
        val snakeRegion: List<Snake>
        when (regionNum) {
            1 -> {snakeRegion = viewModel.loadNESnakes()}
            2 -> {snakeRegion = viewModel.loadNWSnakes()}
            3 -> {snakeRegion = viewModel.loadSESnakes()}
            4 -> {snakeRegion = viewModel.loadSWSnakes()}
            5 -> {snakeRegion = viewModel.loadCSnakes()}
            else -> {snakeRegion = viewModel.loadSnakes()}
        }
        return snakeRegion
    }

    fun setI (i: Int = 0): Int {
        val set = i
        return set
    }

}