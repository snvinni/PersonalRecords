package com.example.personalrecords.view.addexercise

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.makeMainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.personalrecords.databinding.ActivityAddExerciseBinding
import com.example.personalrecords.model.RecordModel
import com.example.personalrecords.service.constants.RecordConstants
import com.example.personalrecords.view.main.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddExerciseBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: AddExerciseViewModel

    //private var viewModel: AddExerciseViewModel by viewModels()
    private var _binding: ActivityAddExerciseBinding? = null
    var recordId: Int = 0

    private val binding get() = _binding!!

    companion object {
        const val TAG = "AddExerciseBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AddExerciseViewModel::class.java)

        _binding = ActivityAddExerciseBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.radioWeight.isChecked = true

        setListeners()
        observe()
        loadData()

    }

    private fun loadData() {
        val bundle = activity?.intent?.extras
        if (bundle != null) {
            recordId = bundle.getInt(RecordConstants.ID.RECORDID)
            viewModel.load(recordId)
        }
    }

    private fun observe() {
        viewModel.saveRecord.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(
                    context,
                    "Record Adicionado com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()
                dismiss()
            } else {
                Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        viewModel.record.observe(viewLifecycleOwner, Observer {
            binding.editExerciseName.setText(it.exerciseName)
            binding.editExerciseRecord.setText(String.format("%.2f", it.exerciseRecord))
            binding.editDate.setText(it.date)
            binding.editNumberRepetitions.setText(it.haveRepetitions)
        })
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener {
            val exerciseName = binding.editExerciseName.text.toString()
            val stringExerciseRecord = binding.editExerciseRecord.text.toString()
            val exerciseRecord = stringExerciseRecord.toDouble()
            val date = binding.editDate.text.toString()
            val exerciseRecordKgs = exerciseRecord / 2

            viewModel.save(
                recordId,
                exerciseName,
                exerciseRecord,
                haveRepetitions(),
                date,
                setMeasurement(),
                repetition(),
                exerciseRecordKgs,
            )
         }

        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun setMeasurement(): String {
        var measurement = ""
        measurement = if (binding.radioTime.isChecked) {
            "minutos"
        } else {
            "lbs"
        }
        return measurement
    }

    private fun haveRepetitions(): String {
        var numberOfRepetitions = binding.editNumberRepetitions.text.toString()
        if (binding.checkBoxNotRepetitions.isChecked) {
            numberOfRepetitions = ""
        }
        return numberOfRepetitions
    }

    private fun repetition(): String {
        var repetition = binding.editNumberRepetitions.text.toString()
        repetition = when (repetition) {
            "1" -> "repetição"
            "" -> ""
            "0" -> ""
            else -> {
                "repetições"
            }
        }
        return repetition
    }
}
