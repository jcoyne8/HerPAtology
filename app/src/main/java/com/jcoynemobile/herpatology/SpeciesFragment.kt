package com.jcoynemobile.herpatology

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jcoynemobile.herpatology.databinding.FragmentSpeciesBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SpeciesFragment : Fragment() {


    private var _binding: FragmentSpeciesBinding? = null
    private val viewModel: EncyclopediaViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        _binding = FragmentSpeciesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val args = SpeciesFragmentArgs.fromBundle(requireArguments())
        val index = args.speciesIndex
        val imageResourceId = args.imageResourceId
        val speciesName = args.speciesName
        val scienceName = args.scienceName
        val venomous = args.venomous
        val habitat = args.habitat
        val length = args.length
        val speciesRange = args.speciesRange
        val coloration = args.coloration
        val notes = args.notes

        val imageView = view.findViewById<ImageView>(R.id.speciesImage)
        val speciesView = view.findViewById<TextView>(R.id.speciesName)
        val scienceView = view.findViewById<TextView>(R.id.speciesScientific)
        val venomView = view.findViewById<TextView>(R.id.speciesVenom)
        val habitatView = view.findViewById<TextView>(R.id.speciesHabitat)
        val lengthView = view.findViewById<TextView>(R.id.speciesLength)
        val rangeView = view.findViewById<TextView>(R.id.speciesRange)
        val colorView = view.findViewById<TextView>(R.id.speciesColor)
        val notesView = view.findViewById<TextView>(R.id.speciesNotes)

        imageView.setImageResource(imageResourceId)
        speciesView.setText(speciesName)
        scienceView.setText(scienceName)
        venomView.setText(venomous)
        habitatView.setText(habitat)
        lengthView.setText(length)
        rangeView.setText(speciesRange)
        colorView.setText(coloration)
        notesView.setText(notes)

        if (venomous == "Venomous") {
            venomView.setTextColor(Color.parseColor("#E6070F"))
        }


        notesView.doOnTextChanged { text, _, _, _ ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    var changeNotes = viewModel.updateNotes(notesView.text.toString(), (index+1))
                    changeNotes
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}