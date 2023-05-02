package com.jcoynemobile.herpatology

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.jcoynemobile.herpatology.databinding.FragmentQuizBinding
import kotlinx.coroutines.launch


class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val viewModel: QuizViewModel by viewModels()
    private val encyclopediaViewModel: EncyclopediaViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root

    }

    //list corresponding to the database elements
    var list = intArrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val snakeLocation = encyclopediaViewModel.loadSnakes()
                binding.yesButton.setOnClickListener {
                    //check the current question index
                    var currentIndex = viewModel.currentIndex
                    if (currentIndex == 13) {
                        for (i in viewModel.currentYesArray) {
                            list[i]++
                        }
                        //find the index of the highest number, and correlate it with the snake's id
                        var max = list.maxOrNull() ?:0
                        var maxIndex: Int
                        //if user is unsure about everything, show the most common PA snake
                        if (max == 0) {
                            maxIndex = 18
                        } else {
                            maxIndex = list.indexOf(max)
                        }
                        //bring up the species page with the appropriate id
                        val snakeImage = encyclopediaViewModel.getSnakeSource(maxIndex + 1)
                        findNavController().navigate(
                            QuizFragmentDirections.actionQuizFragmentToSpeciesFragment((maxIndex), snakeImage, snakeLocation[maxIndex].name, snakeLocation[maxIndex].scientificName, snakeLocation[maxIndex].venom, snakeLocation[maxIndex].habitat, snakeLocation[maxIndex].length, snakeLocation[maxIndex].range, snakeLocation[maxIndex].color, snakeLocation[maxIndex].snakeNotes)
                        )
                    } else {
                        //increment all elements that fit the current array
                        for (i in viewModel.currentYesArray) {
                            list[i]++
                        }
                        viewModel.moveToNext()
                        updateQuestion()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val snakeLocation = encyclopediaViewModel.loadSnakes()
                binding.noButton.setOnClickListener {
                    //check the current question index
                    var currentIndex = viewModel.currentIndex
                    if (currentIndex == 13) {
                        for (i in viewModel.currentNoArray) {
                            list[i]++
                        }
                        //find the index of the highest number, and correlate it with the snake's id
                        var max = list.maxOrNull() ?:0
                        var maxIndex: Int
                        //if user is unsure about everything, show the most common PA snake
                        if (max == 0) {
                            maxIndex = 18
                        } else {
                            maxIndex = list.indexOf(max)
                        }
                        //bring up the species page with the appropriate id
                        val snakeImage = encyclopediaViewModel.getSnakeSource(maxIndex + 1)
                        findNavController().navigate(
                            QuizFragmentDirections.actionQuizFragmentToSpeciesFragment((maxIndex), snakeImage, snakeLocation[maxIndex].name, snakeLocation[maxIndex].scientificName, snakeLocation[maxIndex].venom, snakeLocation[maxIndex].habitat, snakeLocation[maxIndex].length, snakeLocation[maxIndex].range, snakeLocation[maxIndex].color, snakeLocation[maxIndex].snakeNotes)
                        )
                    } else {
                        //increment all elements that fit the current array
                        for (i in viewModel.currentNoArray) {
                            list[i]++
                        }
                        viewModel.moveToNext()
                        updateQuestion()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val snakeLocation = encyclopediaViewModel.loadSnakes()
                binding.unsureButton.setOnClickListener {
                    //check the current question index
                    var currentIndex = viewModel.currentIndex
                    if (currentIndex == 13) {
                        //find the index of the highest number, and correlate it with the snake's id
                        var max = list.maxOrNull() ?:0
                        var maxIndex: Int
                        //if user is unsure about everything, show the most common PA snake
                        if (max == 0) {
                            maxIndex = 18
                        } else {
                            maxIndex = list.indexOf(max)
                        }
                        //bring up the species page with the appropriate id
                        val snakeImage = encyclopediaViewModel.getSnakeSource(maxIndex + 1)
                        findNavController().navigate(
                            QuizFragmentDirections.actionQuizFragmentToSpeciesFragment((maxIndex), snakeImage, snakeLocation[maxIndex].name, snakeLocation[maxIndex].scientificName, snakeLocation[maxIndex].venom, snakeLocation[maxIndex].habitat, snakeLocation[maxIndex].length, snakeLocation[maxIndex].range, snakeLocation[maxIndex].color, snakeLocation[maxIndex].snakeNotes)
                        )
                    } else {
                        viewModel.moveToNext()
                        updateQuestion()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateQuestion() {
        val textId = viewModel.currentQuestionText
        _binding?.quizQuestion?.setText(textId)
    }
}